
  Feature: OMDB Api Test

    @apitest
    Scenario Outline: The user should be able to search films "by search" and "by id"
      Given user searchs "Harry Potter" by search parameter
      When user gets "Harry Potter and the Sorcerer's Stone" film id from the results
      Then user searchs film with this id by id parameter
      And user validates "<title>"> "<year>"> "<released>"> and status code 200 the response data
      Examples:
        |                title                 | year | released    |
        |Harry Potter and the Sorcerer's Stone | 2001 | 16 Nov 2001 |

