Feature: Enter a customer order

	As a customer or customer representative
	I want to enter a customer order
	So that the order can be picked up and delivered

	Scenario: Enter an order with valid data

		Given that I enter an order with valid data
		When I submit that data
		Then the order is stored in the system

	Scenario: Enter an order with invalid data

		Given that I enter an order with invalid data
		When I submit that data
		Then errors are returned