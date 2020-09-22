# 为何要做这个重构呢？
这里我们的函数statement用于打印借阅等信息，但是如果我们要进行扩展的化，比如说以html的格式打印，那么就需要重新来一个htmlStatement函数，当我们需要修改计费逻辑时，这两个函数都要修改，这就很糟糕了。

# 第一步
首先statement的函数过长，需要通过抽取局部变量与参数，然后将其提取参数。
局部变量为each和thisAmount。thisAmount在switch语句中使用，因此可以将其抽出成为函数。

# 第二步
我们上面抽取出来的函数实际上一些命名非常糟糕，比如each, thisAmount，我们要修正它们。

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

