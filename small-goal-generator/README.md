### 根据数据库生成实体类和MVC层生成方式
##### 说明
根据数据库连接生成对应的实体类和MVC层
##### 使用方式
1. 修改公共配置文件 将操作类型指定为Type.MEKE_DATABASE_ENTITY和其他作者信息
2. 修改config/TableConfig目录下相应的数据库配置
3. 运行Generator中的run方法
4. 生成代码完毕

### 生成特殊的实体类方法
##### 说明
当前后端字段出现分歧的时候，需要对返回前端的字段做特殊封装，可以按照规则填写Excel表格，使用BeanUtils.copy方法完成大量耗费时间的工作。
##### 使用方式
1. 修改公共配置文件 将操作类型指定为Type.MEKE_SPECIAL_VO和其他作者信息
2. 按要求填写resources/tempExcel的special.xlsx内容（不要移动其文件位置）
3. 点击运行run方法
4. 生成代码完毕