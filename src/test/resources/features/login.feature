Feature: Scenarios related to login

  Scenario: should validate empty login
    Given user taps sign in
    When logs in
    Then empty credentials are validated