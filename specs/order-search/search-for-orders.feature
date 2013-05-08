Feature: Search for orders

	As a customer
	In order to review my orders
	I need to be able to search for orders in a simple way

	Notes:
	- assume a simple search mechanism a la google, should not have to fill in a complex form
	- really not sure if / how the highlighting / ranking can be done
	- this needs to perform quickly, let's say sub 3 seconds for now

	Scenario: Search for orders using free text
		Given that I have entered orders in the system
		When I search using a free text string
		Then the system should perform a search over all fields possible

	Scenario: Display search results indicating how they were matched and sorting by match relevance
		Given that I have performed a free text search
		When the results are displayed
		Then the system should highlight the field that matched
		And the results should be ranked in order of relevance