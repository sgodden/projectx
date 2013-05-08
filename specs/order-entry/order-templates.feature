Feature: Order templates

	As a logistics provider
	In order to increase my turnover
	I need my system to be easier to use than anyone else's

	As a customer
	In order to enter an order quickly
	I should be able to pick a previous order as a template

	Scenario: Use a recent order as a template

		Given that I have previously entered orders on the system
		When I start to create a new order
		Then the system should present a list of recent orders for me to use as a template


	Scenario: Search for an order to use as a template

		Given that I have previously entered orders on the system
		When I start to create a new order
		Then I should be able to transfer to the order search function so that I can find a specific order to use as a template
		

	Scenario: Create a new order from an existing order

		Given that I have located an existing order
		When I access the order
		Then I should be able to create a new order using that order as a template

