# Entity Relationship Diagram

<img width="1192" alt="Screenshot 2023-11-30 at 15 14 16" src="https://github.com/Kaptajnerne/SocialFoodies_Backend/assets/113175889/8351f9ab-3ef1-40e1-9854-2fd1aa4def23">


### Written in dbdiagram.io

```
Table IceCream {
  iceCreamID int [pk]
  name varchar
  description varchar
  vegan boolean
  nuts boolean
  imageUrl varchar
}

Table CustomerIceCream {
  customerIceCreamID int [pk]
  name varchar
  description varchar
  vegan boolean
  nuts boolean
  imageUrl varchar
  customerID int
}

Table Customer {
  customerID int [pk]
  email varchar
}

Table Admin {
  adminID int [pk]
  email varchar
  password varchar
}

Table Poll {
  pollID int [pk]
  startDate LocalDate
  endDate LocalDate
}

Table Vote {
  voteID int [pk]
  customerID int
  pollID int
  pollOptionID int
}

Table PollOption {
  pollOptionID int [pk]
  iceCreamID int
  customerIceCreamID int
  pollID int
  totalVotes int
}

ref: Customer.customerID < CustomerIceCream.customerID
ref: IceCream.iceCreamID < PollOption.iceCreamID
ref: CustomerIceCream.customerIceCreamID < PollOption.customerIceCreamID
ref: Poll.pollID < PollOption.pollID
ref: Customer.customerID < Vote.customerID
ref: Poll.pollID < Vote.pollID
ref: PollOption.pollOptionID < Vote.pollOptionID
