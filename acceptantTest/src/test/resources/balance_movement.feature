Feature: Acceptance test for balance_movement service

  Background:
    * url 'http://localhost:8080/tallergrupo7/SaldosMovimientos'

  Scenario: Consult service status Ok balancesmovements
    Given request { "data": [ {"account":{ "type":"CUENTA_CORRIENTE", "number":"55131428291"  }, "transaction": { "startDate": "2020-01-01","endDate": "2020-01-30", "description": "Pago Nomina"}}]}
    When method post
    Then status 200
    And match $.data[0].movements[0].id == "8316"

  Scenario: Consult service status Error balancesmovements
    Given request { "data":  {"account":{ "type":"CUENTA_CORRIENTE", "number":"55131428291"  }, "transaction": { "startDate": "2020-01-01","endDate": "2020-01-30", "description": "Pago Nomina"}}}
    When method post
    Then status 400

  Scenario: Consult service status Error500 balancesmovements
    Given request {"account":{ "type":"CUENTA_CORRIENTE", "number":"55131428291"  }, "transaction": { "startDate": "2020-01-01","endDate": "2020-01-30", "description": "Pago Nomina"}}
    When method post
    Then status 500
