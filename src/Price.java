abstract public class Price {
    abstract int getPriceCode();

    double getCharge(int dayRented){
        double result = 0;
        switch (getPriceCode()){
            case Movie.REGULAR:
                result +=2;
                if(dayRented > 2) {
                    result += (dayRented -2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += dayRented * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if(dayRented > 3)
                    result += (dayRented - 3) * 1.5;
                break;
        }
        return result;
    }
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