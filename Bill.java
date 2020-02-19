public class Bill {
    private Car car;
    private OptionalExtraBill optionalExtraBill;
    private double totalBill = 0;

    public double fullPaymentOptionOffer(){
        double priceextra = 0;
        if (optionalExtraBill.isBluetoothConnectivity()){
            priceextra += optionalExtraBill.getpBlootothConnectivity();
        }
        if(optionalExtraBill.isSoundSystem()){
            priceextra += optionalExtraBill.getpSoundSystem();
        }
        if (optionalExtraBill.isSatelliteNavigation()){
            priceextra += optionalExtraBill.getpSatalliteNavigation();
        }

        return priceextra;
    }

    public double payEmi(int years){
        double payMonthEmi = 0;
        if(years>7){
            payMonthEmi = totalBill/(years*12)*0.05;
        }else {
            payMonthEmi= totalBill/(years*12);
        }
        return payMonthEmi;
    }

    public double fullPayment(){
        double cashback = 0;
        cashback = totalBill*0.01;
        return cashback;
    }

    public double discount(Trade trade){
        double discount =0;
        if(trade.isTraded()){
            discount= 0.1;
        }else {
            discount = 0.05;
        }
        discount = totalBill*discount;
        totalBill -=discount;
        return discount;
    }

    public Bill(Car car,OptionalExtraBill optionalExtraBill){
        this.car = car;
        this.optionalExtraBill = optionalExtraBill;
    }

    public double generateTradedBill(int treadAmount){
        totalBill -= treadAmount;
        return totalBill;
    }

    public double calculatebill(){
        totalBill = car.getPrice()+optionalExtraBill.getPriceExtra();
        return totalBill;
    }

    public double getTotalBill(){
        return totalBill;
    }
}
