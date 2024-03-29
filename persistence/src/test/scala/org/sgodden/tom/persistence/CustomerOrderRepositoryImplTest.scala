package org.sgodden.tom.persistence

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.Test
import org.slf4j.LoggerFactory
import org.sgodden.tom.model.impl.{CustomerOrderLine, CustomerOrder}

@ContextConfiguration(locations = Array(
  "classpath:org/sgodden/tom/persistence/beans.xml",
  "classpath:org/sgodden/tom/model/beans.xml"
))
class CustomerOrderRepositoryImplTest extends AbstractTestNGSpringContextTests {

  private def LOG = LoggerFactory.getLogger(classOf[CustomerOrderRepositoryImplTest])

  @Autowired
  val repo: CustomerOrderRepositoryImpl = null

  @Test
  def testCreateAndRetrieve {
    LOG info "ASDASD"
    val order = new CustomerOrder

    order.orderNumber = "ord001"
    order.customerReference = "cr001"

    val line = new CustomerOrderLine(
      packageType = "BOX",
      descriptionOfGoods = "Stuff"
    );

    order.addOrderLine(line)

    repo persist order

    LOG.info("ID: " + order.id)

    val iorder = repo findById order.id

    LOG info iorder.orderNumber

    LOG info iorder.bookingDate.toString

    order.getOrderLines.foreach(line => LOG info line.packageType)
  }

}
