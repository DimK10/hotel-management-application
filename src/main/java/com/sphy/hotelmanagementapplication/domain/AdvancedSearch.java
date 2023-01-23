package com.sphy.hotelmanagementapplication.domain;

import java.time.LocalDate;

public class AdvancedSearch {

    private String location;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private long priceFrom;

    private long priceTo;

    private int adultsRange;

    private int stars;

    private boolean parking;

    private boolean restaurant;

    private boolean roomService;

    private boolean gym;

    private boolean spa;

    private boolean pool;

    private boolean freeWifi;

    private boolean chargingStation;

    private boolean viewToSeaMountain;

    private boolean airConditioning;

    private boolean fireplace;

    private boolean kitchen;

    private boolean refrigerator;

    private boolean miniBar;

    private boolean washingMachine;

    private boolean coffeeTeaMachine;

    private boolean tv;

    private boolean petsAllowed;

    private boolean airportTransport;

    private boolean toiletGrabRails;

    private boolean bathtubGrabRails;

    private boolean showerChair;

    private boolean raisedChair;

    private boolean wheelchairRamps;

    private boolean emergencyPhones;

    private boolean roomsAccessibleElevator;

    private boolean safeDepositBox;

    private boolean bathRobe;

    private boolean hairDryer;

    private boolean babyHighChair;

    public AdvancedSearch(String location, LocalDate checkInDate, LocalDate checkOutDate, long priceFrom,
                          long priceTo, int adultsRange, int stars, boolean parking, boolean restaurant,
                          boolean roomService, boolean gym, boolean spa, boolean pool, boolean freeWifi,
                          boolean chargingStation, boolean viewToSeaMountain, boolean airConditioning,
                          boolean fireplace, boolean kitchen, boolean refrigerator, boolean miniBar,
                          boolean washingMachine, boolean coffeeTeaMachine, boolean tv, boolean petsAllowed,
                          boolean airportTransport, boolean toiletGrabRails, boolean bathtubGrabRails,
                          boolean showerChair, boolean raisedChair, boolean wheelchairRamps, boolean emergencyPhones,
                          boolean roomsAccessibleElevator, boolean safeDepositBox, boolean bathRobe, boolean hairDryer,
                          boolean babyHighChair) {

        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.adultsRange = adultsRange;
        this.stars = stars;
        this.parking = parking;
        this.restaurant = restaurant;
        this.roomService = roomService;
        this.gym = gym;
        this.spa = spa;
        this.pool = pool;
        this.freeWifi = freeWifi;
        this.chargingStation = chargingStation;
        this.viewToSeaMountain = viewToSeaMountain;
        this.airConditioning = airConditioning;
        this.fireplace = fireplace;
        this.kitchen = kitchen;
        this.refrigerator = refrigerator;
        this.miniBar = miniBar;
        this.washingMachine = washingMachine;
        this.coffeeTeaMachine = coffeeTeaMachine;
        this.tv = tv;
        this.petsAllowed = petsAllowed;
        this.airportTransport = airportTransport;
        this.toiletGrabRails = toiletGrabRails;
        this.bathtubGrabRails = bathtubGrabRails;
        this.showerChair = showerChair;
        this.raisedChair = raisedChair;
        this.wheelchairRamps = wheelchairRamps;
        this.emergencyPhones = emergencyPhones;
        this.roomsAccessibleElevator = roomsAccessibleElevator;
        this.safeDepositBox = safeDepositBox;
        this.bathRobe = bathRobe;
        this.hairDryer = hairDryer;
        this.babyHighChair = babyHighChair;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(long priceTo) {
        this.priceTo = priceTo;
    }

    public int getAdultsRange() {
        return adultsRange;
    }

    public void setAdultsRange(int adultsRange) {
        this.adultsRange = adultsRange;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public boolean isChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(boolean chargingStation) {
        this.chargingStation = chargingStation;
    }

    public boolean isViewToSeaMountain() {
        return viewToSeaMountain;
    }

    public void setViewToSeaMountain(boolean viewToSeaMountain) {
        this.viewToSeaMountain = viewToSeaMountain;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isFireplace() {
        return fireplace;
    }

    public void setFireplace(boolean fireplace) {
        this.fireplace = fireplace;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public boolean isMiniBar() {
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) {
        this.miniBar = miniBar;
    }

    public boolean isWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(boolean washingMachine) {
        this.washingMachine = washingMachine;
    }

    public boolean isCoffeeTeaMachine() {
        return coffeeTeaMachine;
    }

    public void setCoffeeTeaMachine(boolean coffeeTeaMachine) {
        this.coffeeTeaMachine = coffeeTeaMachine;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public boolean isAirportTransport() {
        return airportTransport;
    }

    public void setAirportTransport(boolean airportTransport) {
        this.airportTransport = airportTransport;
    }

    public boolean isToiletGrabRails() {
        return toiletGrabRails;
    }

    public void setToiletGrabRails(boolean toiletGrabRails) {
        this.toiletGrabRails = toiletGrabRails;
    }

    public boolean isBathtubGrabRails() {
        return bathtubGrabRails;
    }

    public void setBathtubGrabRails(boolean bathtubGrabRails) {
        this.bathtubGrabRails = bathtubGrabRails;
    }

    public boolean isShowerChair() {
        return showerChair;
    }

    public void setShowerChair(boolean showerChair) {
        this.showerChair = showerChair;
    }

    public boolean isRaisedChair() {
        return raisedChair;
    }

    public void setRaisedChair(boolean raisedChair) {
        this.raisedChair = raisedChair;
    }

    public boolean isWheelchairRamps() {
        return wheelchairRamps;
    }

    public void setWheelchairRamps(boolean wheelchairRamps) {
        this.wheelchairRamps = wheelchairRamps;
    }

    public boolean isEmergencyPhones() {
        return emergencyPhones;
    }

    public void setEmergencyPhones(boolean emergencyPhones) {
        this.emergencyPhones = emergencyPhones;
    }

    public boolean isRoomsAccessibleElevator() {
        return roomsAccessibleElevator;
    }

    public void setRoomsAccessibleElevator(boolean roomsAccessibleElevator) {
        this.roomsAccessibleElevator = roomsAccessibleElevator;
    }

    public boolean isSafeDepositBox() {
        return safeDepositBox;
    }

    public void setSafeDepositBox(boolean safeDepositBox) {
        this.safeDepositBox = safeDepositBox;
    }

    public boolean isBathRobe() {
        return bathRobe;
    }

    public void setBathRobe(boolean bathRobe) {
        this.bathRobe = bathRobe;
    }

    public boolean isHairDryer() {
        return hairDryer;
    }

    public void setHairDryer(boolean hairDryer) {
        this.hairDryer = hairDryer;
    }

    public boolean isBabyHighChair() {
        return babyHighChair;
    }

    public void setBabyHighChair(boolean babyHighChair) {
        this.babyHighChair = babyHighChair;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "AdvancedSearch{" +
                "location='" + location + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", adultsRange=" + adultsRange +
                ", stars=" + stars +
                ", parking=" + parking +
                ", restaurant=" + restaurant +
                ", roomService=" + roomService +
                ", gym=" + gym +
                ", spa=" + spa +
                ", pool=" + pool +
                ", freeWifi=" + freeWifi +
                ", chargingStation=" + chargingStation +
                ", viewToSeaMountain=" + viewToSeaMountain +
                ", airConditioning=" + airConditioning +
                ", fireplace=" + fireplace +
                ", kitchen=" + kitchen +
                ", refrigerator=" + refrigerator +
                ", miniBar=" + miniBar +
                ", washingMachine=" + washingMachine +
                ", coffeeTeaMachine=" + coffeeTeaMachine +
                ", tv=" + tv +
                ", petsAllowed=" + petsAllowed +
                ", airportTransport=" + airportTransport +
                ", toiletGrabRails=" + toiletGrabRails +
                ", bathtubGrabRails=" + bathtubGrabRails +
                ", showerChair=" + showerChair +
                ", raisedChair=" + raisedChair +
                ", wheelchairRamps=" + wheelchairRamps +
                ", emergencyPhones=" + emergencyPhones +
                ", roomsAccessibleElevator=" + roomsAccessibleElevator +
                ", safeDepositBox=" + safeDepositBox +
                ", bathRobe=" + bathRobe +
                ", hairDryer=" + hairDryer +
                ", babyHighChair=" + babyHighChair +
                '}';
    }
}

