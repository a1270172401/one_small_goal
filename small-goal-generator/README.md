### 根据数据库生成实体类和MVC层生成方式
1. 修改公共配置文件 将操作类型指定为Type.MEKE_DATABASE_ENTITY和其他作者信息
2. 修改config/TableConfig目录下相应的数据库配置
3. 运行Generator中的run方法
4. 生成代码完毕

### 生成特殊的实体类方法
1. 修改公共配置文件 将操作类型指定为Type.MEKE_SPECIAL_VO和其他作者信息
2. 按要求填写resources/tempExcel的special.xlsx内容（不要移动其位置）
3. 点击运行run方法
4. 生成代码完毕