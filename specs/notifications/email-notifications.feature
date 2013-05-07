Feature: Email notifications
	In order to be kept up to date about the progress of my order
	As a Customer
	I should receive email notifications at key points in the life cycle of my Shipments

	Scenario: Notification parties on the Customer Profile should be notified

		Given that I have entered an Order
		When a notifiable event occurs against that Order
		Then an email should be sent to the email addresses on the Customer's profile

	Scenario Outline: Send email notifications when an event is manually marked against an order

		Given that I have confirmed an Order
		When I manually mark action <action> as having occurred
		Then an email should be sent to the notification parties for the Order

		Examples:
		| action |
		| Collected |
		| Arrived at collection hub |
		| Departed from collection hub |
		| Arrived at delivery hub      |
		| Departed from delivery hub   |
		| Delivered                    |

	Scenario: Send email notifications when all or part of an order is scanned at any location

		Given that I have confirmed an Order
		When all or part of an order is scanned at any location
		Then an email should be sent to the notification email address on my Customer Profile
		And an email should be sent to additional notification email addresses that I have put on the Order