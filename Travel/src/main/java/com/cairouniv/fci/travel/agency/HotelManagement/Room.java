package com.cairouniv.fci.travel.agency.HotelManagement;

public class Room {
  public enum RoomType {
    SINGLE,
    DOUBLE,
    FAMILY;
  }
  private int RoomNumber;
  private RoomType Type;
  private double PricePerNight;
  private String RoomFloorNo;
  private boolean IsAvailable;
  public Room(int roomNumber, RoomType type, double pricePerNight, String roomFloorNo, Boolean AV) {
    RoomNumber = roomNumber;
    Type = type;
    PricePerNight = pricePerNight;
    RoomFloorNo = roomFloorNo;
    IsAvailable = AV != null ? AV : false;
  }
  public int getRoomNumber() {
    return RoomNumber;
  }
  public RoomType getType() {
    return Type;
  }
  public double getPricePerNight() {
    return PricePerNight;
  }
  public String getRoomFloorNo() {
    return RoomFloorNo;
  }
  public void setRoomNumber(int roomNumber) {
    RoomNumber = roomNumber;
  }
  public void setAvailable(boolean available) {
    IsAvailable = available;
  }
  public void setType(RoomType type) {
    Type = type;
  }
  public void setPricePerNight(double pricePerNight) {
    PricePerNight = pricePerNight;
  }
  public void setRoomFloorNo(String roomFloorNo) {
    RoomFloorNo = roomFloorNo;
  }
  public boolean Checkavailbility() {
    System.out.println("Room availability checked: " + IsAvailable);
    return IsAvailable;
  }
  public double priceCalculator(int NightsNo) {
    return PricePerNight * NightsNo;
  }
  public String getRoomDetails() {
    return String.format(
            String.valueOf(RoomNumber),
            Type,
            PricePerNight,
            RoomFloorNo,
            IsAvailable ? "Available" : "Not Available"
    );
  }
}
