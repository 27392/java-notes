## 设计模式

### 目录

|  名称 |   描述     |
|---- |    ----       | 
| [策略模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/strategy/%E7%AD%96%E7%95%A5%E6%A8%A1%E5%BC%8F.md)| 定义了算法族,分别封装起来,让它们之间可以互相替换,此模式让算法的变化独立于使用算法的客户|
| [观察者模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/observer/%E8%A7%82%E5%AF%9F%E8%80%85%E6%A8%A1%E5%BC%8F.md)| 定义了对象之间的一对多关系依赖,这样一来,当一个对象改变状态时,它的所有依赖者都会收到通知并自动更新|
| [装饰器模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/decorator/%E8%A3%85%E9%A5%B0%E5%99%A8%E6%A8%A1%E5%BC%8F.md)| 动态地将责任附加到对象上.若要拓展功能,装饰器提供了比继承更有弹性的替代方案|
| [工厂方法模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/factory/%E5%B7%A5%E5%8E%82%E6%A8%A1%E5%BC%8F.md)| 定义了一个创建对象的接口,但由子类决定要实例化的类是哪一个.工厂方法让类把实例化推迟到子类 |
| [抽象工厂模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/factory/%E5%B7%A5%E5%8E%82%E6%A8%A1%E5%BC%8F.md)| 提供一个接口,用于创建相关或依赖对象的家族,而不需要明确指定具体类 |
| [单例模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/singleton/%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F.md)| 确保一个类只有一个实例,并提供一个全局访问点 |
| [命令模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/command/%E5%91%BD%E4%BB%A4%E6%A8%A1%E5%BC%8F.md)| 将"请求"封装成对象,以便使用不同的请求、队列或者日志来参数化其他对象.命令模式也支持可撤销的操作 |
| [适配器模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/adapter/%E9%80%82%E9%85%8D%E5%99%A8%E6%A8%A1%E5%BC%8F.md)| 将一个类的接口,转换成客户期望的另一个接口.适配器让原本接口不兼容的类可以合作无间 |
| [外观模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/facade/%E5%A4%96%E8%A7%82%E6%A8%A1%E5%BC%8F.md)| 提供了一个统一的接口,用来访问子系统中的一群接口. 外观定义了一个高层接口,让子系统更容易使用 |
| [模板方法模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/template/%E6%A8%A1%E6%9D%BF%E6%96%B9%E6%B3%95%E6%A8%A1%E5%BC%8F.md)| 在一个方法定义一个算法骨架,而将一些步骤延迟到子类中.模板方法是的子类可以在不改变算法结构的情况下,重新定义算法中的某些步骤 |
| [迭代器模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/iterator/%E8%BF%AD%E4%BB%A3%E5%99%A8%E6%A8%A1%E5%BC%8F.md)| 提供一种方法顺序访问一个聚合对象中的各个元素,而又不暴露其内部的表示 |
| [组合模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/composite/%E7%BB%84%E5%90%88%E6%A8%A1%E5%BC%8F.md)| 允许你将对象组合成树形结构来表现"整体/部分"层次结构. 组合能让客户以一致的方式处理个别对象以及对象组合 |
| [状态模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/state/%E7%8A%B6%E6%80%81%E6%A8%A1%E5%BC%8F.md)| 允许对象在内部状态改变时改变它的行为,对象看起来好像修改了它的类 |
| [代理模式](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/proxy/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F.md)| 为另一个对象提供一个替身或占位符以控制这个对象的访问 |

---
[综合案例(多个模式结合使用)](https://github.com/27392/java-notes/blob/master/src/main/java/cn/haohaoli/book/headfirst/demo/%E6%A1%88%E4%BE%8B.md)
