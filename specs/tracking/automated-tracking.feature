Feature: Track shipments

	As a customer or customer representative
	In order to know where a shipment currently is
	I need the system to track the current location of packages

	Notes:
	- we need a manual option in the case of scanner or process failure
	- to operate in multiple time zones we need to know the time zone of the location at which the scanner is registered
	- therefore we need a new Location entity which has a time zone, prob falling back to country time zone if not specified.

	Scenario: A package is scanned on arrival at a particular location

		Given that a package is unloaded at a particular location
		When the package is scanned
		Then the package is registered as having arrived at the scanner's location

	Scenario: A package is scanned on departure from a particular location

		Given that a package is loaded at a particular location
		When the package is scanned
		Then the package is registered as having departed from the scanner's location

	Scenario: Manual indication of shipment arrival or departure is required

		Given that a shipment exists
		And I am a customer representative
		When I mark that shipment as