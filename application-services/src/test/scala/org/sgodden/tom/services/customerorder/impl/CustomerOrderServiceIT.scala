package org.sgodden.tom.services.customerorder.impl

import org.sgodden.tom.services.customerorder.CustomerOrderService
import org.testng.Assert._
import org.sgodden.tom.model.{ValidationException, CustomerOrderStatus, ICustomerOrder}
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.{BeforeMethod, Test}

class CustomerOrderServiceIT extends AbstractIntegrationTest {

  @Autowired private var customerOrderService: CustomerOrderService = null

  @BeforeMethod def beforeTest {
    for (order <- customerOrderService.findAll) {
      customerOrderService.remove(order.getId)
    }
  }

  @Test
  def testCreate {
    var order: ICustomerOrder = customerOrderService.create
    assertEquals(order.getStatus, CustomerOrderStatus.NEW)
  }

  @Test
  def testPersist {
    createOrder(1)
    assertEquals(customerOrderService.findAll.size, 1)
  }

  @Test
  def testInvalidOrderIsNotPersisted {
    val order = makeOrder(1);
    order.setCustomerReference("wrong")
    try {
      customerOrderService.persist(order)
    } catch {
      case ve: ValidationException => {}
    }
    assertEquals(customerOrderService.findAll.size, 0)
  }

  @Test
  def testFindAll {
    (1 to 10).foreach(createOrder(_))
    assertEquals(customerOrderService.findAll.size, 10)
  }

  @Test
  def shouldFailWithNullOrderNumber {
    var order: ICustomerOrder = customerOrderService.create
    try {
      customerOrderService.persist(order)
      fail("Validation exception should have been thrown")
    } catch {
      case ex: ValidationException => {}
    }
  }

  private def createOrder(seq: Int): Unit = {
    customerOrderService.persist(makeOrder(seq))
  }

  private def makeOrder(seq: Int) = {
    var order: ICustomerOrder = customerOrderService.create
    order.setCustomerReference("crREFERENCE: " + seq)
    order.setOrderNumber("ORDER NUMBER: " + seq)
    order
  }
}
