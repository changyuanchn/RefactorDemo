abstract public class Price {
    abstract int getPriceCode();
}

class ChildrenPrice extends Price{
    int getPriceCode(){
        return Movie.CHILDRENS;
    }
}

class NewReleasePrice extends Price{
    int getPriceCode(){
        return Movie.NEW_RELEASE;
    }
}

class RegularPrice extends Price{
    int getPriceCode(){
        return Movie.REGULAR;
    }
}