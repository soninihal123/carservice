import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String[] carTypes = {"Hatch Back","Saloon","Estate"};
        String[] carPrice = {"5.35 lakh","4.95 lak","6.25 lakh"};
        int selectedCar = 3; // for selection validation

        Car car; // gonna use for creating object
        Bill bill;
        Trade trade = new Trade();
        Scanner sc = new Scanner(System.in);


        Main objectMain = new Main(); // main object

        //showing car using show car method
        objectMain.showCars(carTypes,carPrice);

        while(true) {
            //calling select car method
            selectedCar = objectMain.selectCar(sc, selectedCar, carPrice, carTypes);

            //creating car object according to user selection
            if (selectedCar == 0) {
                car = new Hatchback();
                break;
            } else if (selectedCar == 1) {
                car = new Saloon();
                break;
            } else if (selectedCar == 2) {
                car = new Estate();
                break;
            } else {
                System.out.println("please again enter the right selection");
            }
        }

        System.out.print("Do you want some Optional Extras If yes press 1 and if No press 0\n ");
        int askOptionalExtra = sc.nextInt();
        OptionalExtraBill optionalExtraBill = new OptionalExtraBill();

        if(askOptionalExtra == 1){
             OptionalExtraBill optionalExtraBill1 =  objectMain.optonalExtraFeatures(sc,optionalExtraBill);
             bill = new Bill(car, optionalExtraBill1);
             System.out.println("Your Total Bill is :"+ bill.calculatebill());
        }else{
            bill = new Bill(car,optionalExtraBill);
            System.out.println("Your Total Bill is :"+ bill.calculatebill());
        }
        System.out.println("\nAre you the existing customer if yes press 1 and if no press 0");
        int exsiting = sc.nextInt();
        if(exsiting==1){
            System.out.println("Hey we have the facility of Trade the cars do you want to use If yes press 1 if no press 0 ");
            if(sc.nextInt() == 1 ){
                int tradedPrice = objectMain.tradeCar(sc,trade);
                System.out.println("Bill after trade : "+bill.generateTradedBill(tradedPrice));
            }else {
                System.out.println("You have not traded so Your total Bill is : "+bill.getTotalBill());
            }
        }else {
            System.out.println("Your total bill is : "+bill.getTotalBill());
        }
        System.out.println("Discounted Price : " +bill.discount(trade));
        System.out.println("You have Selected : \""+car.getModel()+"\" Model And Price of the Car is : \""+car.getPrice()+"\"");

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Your CAR comes with these Extra features:");
        if(optionalExtraBill.isLuxurySeats()){
            System.out.println("\"Luxury Seats\" ");
        }
        if(optionalExtraBill.isSatelliteNavigation()){
            System.out.println("\"Satalite Navigation\" ");
        }
        if(optionalExtraBill.isParkingSensors()){
            System.out.println("\"Parking Sensors\" ");
        }
        if(optionalExtraBill.isBluetoothConnectivity()){
            System.out.println("\"Bluetooth Connectivity\" ");
        }
        if(optionalExtraBill.isSoundSystem()){
            System.out.println("\"Sound System\" ");
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("After Billing and Including Discount Your Total Bill is : "+bill.getTotalBill());
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("Choose the payment mode Press (1: For Full payment) and Press (0: For EMI) ");
        System.out.println("Note : There is 1% cashback if you do FULL PAYMENT");
        int payment = sc.nextInt();
        if(payment !=0 ){
            System.out.println("Either You will get 1% cashback which is : "+bill.fullPayment());
            System.out.println("Or any Optional Extra free (excluding Luxury Seats and Parking Sensor) If you have selected ");
            double fullPaymentOffer = bill.fullPaymentOptionOffer();

            if(fullPaymentOffer<bill.fullPayment()){
                System.out.println("1: Chashback is more Profitable offer where cashback is :" + bill.fullPayment());
                System.out.println("2: Getting Optional Extra will reduce only : "+ bill.fullPaymentOptionOffer());
            }else {
                System.out.println("1: Getting Optional Extra is more Profitable offer where Offer price is :" + bill.fullPaymentOptionOffer());
                System.out.println("2: Getting Cashback will reduce only : "+ bill.fullPayment());
            }

        }else {
            System.out.println("You have selected EMI Please Enter how many Years EMI you want : ");
            System.out.println("NOTE: 1: If you will select EMI for over 4 years so You will not Charged  \n2: IF you will select 7 Or more year EMI Then You will Charge with 5% increase in Total Bill .");
            System.out.print("Enter the Number Of years : ");
            int years = sc.nextInt();
            double emi = bill.payEmi(years);
            System.out.println("Your monthly EMI is : "+emi+ "pre month  for : "+years+" years. ");
        }





    }

    public int tradeCar(Scanner sc,Trade trade){
        trade.setTraded(true);
        System.out.println("Enter the Snazzy Autos Here");
        int tradePrice = sc.nextInt();
        trade.setTradePrice(tradePrice);
        return trade.getTradePrice();
    }

    public OptionalExtraBill optonalExtraFeatures(Scanner sc,OptionalExtraBill optionalExtraBill){
        for (int i = 0;i<optionalExtraBill.priceListOptionalExtra.length;i++) {
            System.out.println(optionalExtraBill.priceListOptionalExtra[i]);
        }
        System.out.println("\nSelect features if yes Then Press any Number If no Press \" 0 \"\n ");
        System.out.println(optionalExtraBill.priceListOptionalExtra[0]);
        if(sc.nextInt()!=0)
            optionalExtraBill.setLuxurySeats(true);

        System.out.println(optionalExtraBill.priceListOptionalExtra[1]);
        if(sc.nextInt()!=0)
            optionalExtraBill.setSatelliteNavigation(true);

        System.out.println(optionalExtraBill.priceListOptionalExtra[2]);
        if(sc.nextInt()!=0)
            optionalExtraBill.setParkingSensors(true);

        System.out.println(optionalExtraBill.priceListOptionalExtra[3]);
        if(sc.nextInt()!=0)
            optionalExtraBill.setBluetoothConnectivity(true);

        System.out.println(optionalExtraBill.priceListOptionalExtra[4]);
        if(sc.nextInt()!=0)
            optionalExtraBill.setSoundSystem(true);

        return optionalExtraBill;
    }

    //showing car
    public void showCars(String[] carTypes,String[] carPrice){
        System.out.println("Here is the list of all the cars With price\n");
        for (int i = 0; i<carTypes.length;i++) {
            System.out.println("Model "+(i+1)+":"+carTypes[i]+"| Price : "+carPrice[i]);
        }
    }

    //selecting car
    public int selectCar(Scanner sc, int selectedCar,String[] carPrice,String[] carTypes){
        System.out.println("\nNow to Purchase specified model You need to enter the model Number");
        System.out.print("Please Enter Model Number Here : ");
        selectedCar = sc.nextInt()-1;

        if(selectedCar < 3)
            System.out.println("\nYou have Selected \""+carTypes[selectedCar]+"\" And the Price of the car is " +carPrice[selectedCar]+"\n");
        else {
            System.out.println("you have selected invalid model ThankYou");
        }
        return selectedCar;
    }
}
