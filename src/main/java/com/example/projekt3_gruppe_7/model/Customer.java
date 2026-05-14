package com.example.projekt3_gruppe_7.model;

public class Customer {
   private Long customerId;
   private String name;
   private String phone;
   private String email;

   public Customer(Long customerId, String name, String phone, String email) {
      this.customerId = customerId;
      this.name = name;
      this.phone = phone;
      this.email = email;
   }

   public Customer(String name, String phone, String email) {
      this.name = name;
      this.phone = phone;
      this.email = email;
   }

   public Long getCustomerId() {
      return customerId;
   }

   public String getName() {
      return name;
   }

   public String getPhone() {
      return phone;
   }

   public String getEmail() {
      return email;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
