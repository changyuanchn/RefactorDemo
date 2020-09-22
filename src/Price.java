abstract public class Price {
    abstract int getPriceCode();

    abstract double getCharge(int dayRented);

    int getFrequentRenterPoints(int dayRented){
        return 1;
    }
}

class ChildrenPrice extends Price{
    int getPriceCode(){
        return Movie.CHILDRENS;
    }

    double getCharge(int dayRented){
        double result = 1.5;
        if(dayRented > 3)
            result += (dayRented - 3) * 1.5;
        return result;
    }
}

class NewReleasePrice extends Price{
    int getPriceCode(){
        return Movie.NEW_RELEASE;
    }
    double getCharge(int dayRented){
        return dayRented * 3;
    }

    int getFrequentRenterPoints(int dayRented){
        return (dayRented > 1) ? 2 : 1;
    }
}

class RegularPrice extends Price{
    int getPriceCode(){
        return Movie.REGULAR;
    }
    double getCharge(int dayRented){
        double result = 2;
        if(dayRented > 2) {
            result += (dayRented -2) * 1.5;
        }
        return result;
    }
}