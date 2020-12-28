package utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.StringUtils;
import common.Special;
import config.CommonConfig;
import config.SpecialVoConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取Excel
 *
 * @author 念着倒才子傻
 */
public class ExcelUtils {
    public static void repeatedRead() {
        String fileName = SpecialVoConfig.PATH;
        // 读取全部sheet
        // 这里需要注意 DataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DataListener里面写
        EasyExcel.read(fileName, Special.class, new DataListener()).doReadAll();
    }

    static class DataListener extends AnalysisEventListener<Special> {
        /**
         * 缓存方法名称
         */
        private static String TAXON = "";

        List<Special> list = new ArrayList<Special>();

        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @SneakyThrows
        @Override
        public void invoke(Special data, AnalysisContext context) {
            System.out.println("解析到一条数据 ==> " + data.toString());
            list.add(data);
            // 如果类名变更则执行业务逻辑，后续再执行后面的
            System.out.println(!StringUtils.isEmpty(TAXON) && !TAXON.equals(data.getTaxon()));
            if (!StringUtils.isEmpty(TAXON) && !TAXON.equals(data.getTaxon())) {
                Special special = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                saveData();
                // 存储完成清理 list
                list.clear();
                list.add(special);
            }
            TAXON = data.getTaxon();
        }

        /**
         * 解析完毕所有数据调用
         *
         * @param context
         */
        @SneakyThrows
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            saveData();
            System.out.println("所有数据解析完成！");
        }

        /**
         * 处理数据
         */
        private void saveData() throws IOException, TemplateException {
            System.out.println("处理数据 ==> " + list);
            Map root = new HashMap();
            String outRoot = CommonConfig.OUT_PATH;
            root.put("specials", list);
            root.put("taxon", list.get(0).getTaxon());
            root.put("packageFirst", CommonConfig.PACKAGE_FIRST);
            root.put("package", CommonConfig.BASE_PACKAGE);
            root.put("classNameLower", list.get(0).getTaxon());
            root.put("date", CommonConfig.DATE);
            root.put("author", CommonConfig.AUTHOR);
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
            String templateDir = this.getClass().getClassLoader().getResource("votemp").getPath();
            File tdf = new File(templateDir);
            List<File> files = FileHelper.findAllFile(tdf);
            for (File f : files) {
                String parentDir = "";
                if (f.getParentFile().compareTo(tdf) != 0) {
                    parentDir = f.getParent().split("templates")[1];
                }
                cfg.setClassForTemplateLoading(this.getClass(), "/votemp" + parentDir);

                Template template = cfg.getTemplate(f.getName(), "UTF-8");
                String parentFileDir = FileHelper.genFileDir(parentDir, root);
                parentFileDir = parentFileDir.replace(".", "/");
                String file = FileHelper.genFileDir(f.getName(), root).replace(".ftl", ".java");
                File newFile = FileHelper.makeFile(outRoot + parentFileDir + "/" + file);
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
                template.process(root, out);
            }
        }
    }
}
