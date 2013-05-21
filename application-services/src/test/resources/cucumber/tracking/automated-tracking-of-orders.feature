Feature: Track shipments

	In order to know where a shipment currently is
	As a customer or customer representative
	I need the system to track the current location of orders

	Notes:
	- we need a manual option in the case of scanner or process failure
	- to operate in multiple time zones we need to know the time zone of the location at which the scanner is registered
	- therefore we need a new Location entity which has a time zone, probably falling back to country time zone if not specified.

	Scenario: A manifest is scanned as having been unloaded

		Given that a vehicle is unloaded at a particular location
		When the cargo manifest is scanned
		Then the trip is marked as having arrived at that location


	Scenario: A manifest is scanned on departure from a particular location

		Given that a vehicle is loaded at a particular location
		When the cargo manifest is scanned
		Then the trip is marked as having departed from that location


	Scenario: Customer orders fully allocated to trips are marked as having departed when the trip departs

		Given that a customer order is fully allocated to a trip
		When the trip is marked as having departed from a location
		Then the customer order is marked as having departed from that location


	Scenario: Customer orders fully allocated to trips are marked as having arrived when the trip arrives

		Given that a customer order is fully allocated to a trip
		When the trip is marked as having arrived at a location
		Then the customer order is marked as having arrived at that location