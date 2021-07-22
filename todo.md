# todoList 
> 用于记录工程的代办事项 

- [x] 完善首页
  - [x] 完善header
  - [x] 完善footer
  - [x] 完善main
- [x] 修复header过于耦合的问题
- [ ] 完善注册时的表单检查
- [ ] 课程的创建
- [ ] 课程的修改和删除
- [ ] 用户主页
- [ ] 题目的上传
- [x] 后台管理

## 2020/12/31
完成了登录和注册功能

## 2020/1/1
完善header和footer，搭建好了首页

### 我学到了
- 在thymeleaf中使用shiro进行权限管理

- 在thymeleaf中获取contextPath

- mybatis如果不开启驼峰映射会导致数据库的驼峰命名字段无法映射

    方法：在`application.properties`中配置`mybatis.configuration.map-underscore-to-camel-case=true`


