# 为何要做这个重构呢？
这里我们的函数statement用于打印借阅等信息，但是如果我们要进行扩展的化，比如说以html的格式打印，那么就需要重新来一个htmlStatement函数，当我们需要修改计费逻辑时，这两个函数都要修改，这就很糟糕了。

# 第一步
首先statement的函数过长，需要通过抽取局部变量与参数，然后将其提取参数。
局部变量为each和thisAmount。thisAmount在switch语句中使用，因此可以将其抽出成为函数。

# 第二步
我们上面抽取出来的函数实际上一些命名非常糟糕，比如each, thisAmount，我们要修正它们
# 第三步
我们抽取出来的函数跟customer没有任何关系，它利用来自rental的信息，因此把它放在customer里面并不合适，因此将其移动到Rental类中
然后修改适配。

# 第四步
修改了这些后，实际上thisAmount也没什么用了，可以被直接替换掉了。

# 第五步
计算常客积分这部分代码实际上也跟customer没多大关系，这部分也是跟Rental有关，因此将其提炼出函数，然后放到Rental中

# 第六步
我们发现还是有两个临时变量，这里要将其优化掉，依据Replace Tmp with Query.
double totalAmount = 0;
int frequentRenterPoints = 0;
由于这两个变量在循环中使用，所以我们抽取的函数也要抽取循环。

# 第七步
经过上面的重构，当我们再添加一个功能的时候，就很简单了，而且如果要修改一个计费逻辑的话，就只需要修改一处代码即可。
htmlStatement

# 第八步
下面要处理的是switch语句，一般来说不要在另一个对象的属性基础上使用switch语句，即使不得不使用的场景下，也要在对象自己的数据属性上使用。
我们的switch在Rental类里面，但是用到了movie的属性数据，这暗示我们要将getCharge移动到Movie里面。因此这一步我们就来处理这个问题

同理这里面getFrequentRenterPoints也用到了Movie类的数据，一样的做一下搬迁

# 第九步
下面可以将不同的电影类型作为子类来分别处理，这样每个类型就有自己的计费方法了，这样就能解决掉switch语句了。但是这样也有个问题，就是每个movie实际上
在自己的生命周期内是可以改变自己的类型的，但是对象却不可以改变类，这就尴尬了，因此需要用到state设计模式。

首先我们用一个函数setPriceCode(priceCode)来代替_priceCode，然后新创建一个price类，这样就解决了上面的问题，用状态解决了问题。

# 第十步
下面我们将getCharge搬迁到Price类里面去。

# 第十一步
下面就是拆分getCharge到各个子类中。这样也就分解了switch，然后将父类的函数声明为abstract

同理也要拆分getFrequentRenterPoints

# 第十二步
以上就是所有的修改，通过这些修改，当我们无论修改价格还是积分或者影片类型等的时候，都可以很简单的进行修改，不会影响到其他的类。