# Entity Relationship Diagram

![image](https://github.com/Kaptajnerne/SocialFoodies_Backend/assets/113116068/3f505fa5-eb1d-46f4-8e2e-d61b3ff32ba0)

### Written in dbdiagram.io

```
Table IceCream {
  id int [pk]
  name varchar
  description varchar
  vegan boolean
  nuts boolean
}

Table CustomerIceCream {
  id int [pk]
  name varchar
  description varchar
  vegan boolean
  nuts boolean
  customerID int
  status varchar //accepted, rejected, pending
}

Table Customer {
  id int [pk]
  email varchar
}

Table Administrator {
  id int [pk]
  username varchar
  password varchar
  email varchar
}

Table Poll {
  id int [pk]
  startDate date
  endDate date
  status varchar
  iceCreamOption1 int
  iceCreamOption2 int 
  iceCreamOption3 int 
}

Table Vote {
  id int [pk]
  customerID int
  pollID int
  votedOption int
}

ref: Customer.id < Vote.customerID
ref: Poll.id < Vote.pollID
ref: IceCream.id < Poll.iceCreamOption1
ref: IceCream.id < Poll.iceCreamOption2
ref: IceCream.id < Poll.iceCreamOption3
ref: CustomerIceCream.id < Poll.iceCreamOption1
ref: CustomerIceCream.id < Poll.iceCreamOption2
ref: CustomerIceCream.id < Poll.iceCreamOption3
ref: Customer.id < CustomerIceCream.customerID
```
