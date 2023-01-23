package com.sphy.hotelmanagementapplication.domain;

import java.time.LocalDate;

public class AdvancedSearch {

    private String location;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long priceFrom;

    private Long priceTo;

    private Integer adultsRange;

    private Integer stars;

    private Boolean parking;

    private Boolean restaurant;

    private Boolean roomService;

    private Boolean gym;

    private Boolean spa;

    private Boolean pool;

    private Boolean freeWifi;

    private Boolean chargingStation;

    private Boolean viewToSeaMountain;

    private Boolean airConditioning;

    private Boolean fireplace;

    private Boolean kitchen;

    private Boolean refrigerator;

    private Boolean miniBar;

    private Boolean washingMachine;

    private Boolean coffeeTeaMachine;

    private Boolean tv;

    private Boolean petsAllowed;

    private Boolean airportTransport;

    private Boolean toiletGrabRails;

    private Boolean bathtubGrabRails;

    private Boolean showerChair;

    private Boolean raisedChair;

    private Boolean wheelchairRamps;

    private Boolean emergencyPhones;

    private Boolean roomsAccessibleElevator;

    private Boolean safeDepositBox;

    private Boolean bathRobe;

    private Boolean hairDryer;

    private Boolean babyHighChair;

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

    public Long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Long priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getAdultsRange() {
        return adultsRange;
    }

    public void setAdultsRange(Integer adultsRange) {
        this.adultsRange = adultsRange;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Boolean isParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Boolean restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(Boolean roomService) {
        this.roomService = roomService;
    }

    public Boolean isGym() {
        return gym;
    }

    public void setGym(Boolean gym) {
        this.gym = gym;
    }

    public Boolean isSpa() {
        return spa;
    }

    public void setSpa(Boolean spa) {
        this.spa = spa;
    }

    public Boolean isPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }

    public Boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(Boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public Boolean isChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(Boolean chargingStation) {
        this.chargingStation = chargingStation;
    }

    public Boolean isViewToSeaMountain() {
        return viewToSeaMountain;
    }

    public void setViewToSeaMountain(Boolean viewToSeaMountain) {
        this.viewToSeaMountain = viewToSeaMountain;
    }

    public Boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(Boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public Boolean isFireplace() {
        return fireplace;
    }

    public void setFireplace(Boolean fireplace) {
        this.fireplace = fireplace;
    }

    public Boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(Boolean kitchen) {
        this.kitchen = kitchen;
    }

    public Boolean isRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(Boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public Boolean isMiniBar() {
        return miniBar;
    }

    public void setMiniBar(Boolean miniBar) {
        this.miniBar = miniBar;
    }

    public Boolean isWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(Boolean washingMachine) {
        this.washingMachine = washingMachine;
    }

    public Boolean isCoffeeTeaMachine() {
        return coffeeTeaMachine;
    }

    public void setCoffeeTeaMachine(Boolean coffeeTeaMachine) {
        this.coffeeTeaMachine = coffeeTeaMachine;
    }

    public Boolean isTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(Boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Boolean isAirportTransport() {
        return airportTransport;
    }

    public void setAirportTransport(Boolean airportTransport) {
        this.airportTransport = airportTransport;
    }

    public Boolean isToiletGrabRails() {
        return toiletGrabRails;
    }

    public void setToiletGrabRails(Boolean toiletGrabRails) {
        this.toiletGrabRails = toiletGrabRails;
    }

    public Boolean isBathtubGrabRails() {
        return bathtubGrabRails;
    }

    public void setBathtubGrabRails(Boolean bathtubGrabRails) {
        this.bathtubGrabRails = bathtubGrabRails;
    }

    public Boolean isShowerChair() {
        return showerChair;
    }

    public void setShowerChair(Boolean showerChair) {
        this.showerChair = showerChair;
    }

    public Boolean isRaisedChair() {
        return raisedChair;
    }

    public void setRaisedChair(Boolean raisedChair) {
        this.raisedChair = raisedChair;
    }

    public Boolean isWheelchairRamps() {
        return wheelchairRamps;
    }

    public void setWheelchairRamps(Boolean wheelchairRamps) {
        this.wheelchairRamps = wheelchairRamps;
    }

    public Boolean isEmergencyPhones() {
        return emergencyPhones;
    }

    public void setEmergencyPhones(Boolean emergencyPhones) {
        this.emergencyPhones = emergencyPhones;
    }

    public Boolean isRoomsAccessibleElevator() {
        return roomsAccessibleElevator;
    }

    public void setRoomsAccessibleElevator(Boolean roomsAccessibleElevator) {
        this.roomsAccessibleElevator = roomsAccessibleElevator;
    }

    public Boolean isSafeDepositBox() {
        return safeDepositBox;
    }

    public void setSafeDepositBox(Boolean safeDepositBox) {
        this.safeDepositBox = safeDepositBox;
    }

    public Boolean isBathRobe() {
        return bathRobe;
    }

    public void setBathRobe(Boolean bathRobe) {
        this.bathRobe = bathRobe;
    }

    public Boolean isHairDryer() {
        return hairDryer;
    }

    public void setHairDryer(Boolean hairDryer) {
        this.hairDryer = hairDryer;
    }

    public Boolean isBabyHighChair() {
        return babyHighChair;
    }

    public void setBabyHighChair(Boolean babyHighChair) {
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

