import org.scalatest._

class BillTest extends FunSuite with Matchers {

  test("Bill total of order contains drinks only should have 0 service charge") {
    Bill.calcBill(List("Cola", "Coffee")) should be(1.5)
  }

  test("Bill total of order contains cold food should have 10% service charge") {
    Bill.calcBill(List("Cola", "Coffee", "Cheese Sandwich")) should be(3.85)
  }

  test("Bill total of order contains hot food should have 20% service charge") {
    Bill.calcBill(List("Cola", "Coffee", "Cheese Sandwich", "Steak Sandwich")) should be(9.6)
  }

  test("Hot food service charge should be capped at $20") {
    Bill.calcBill(List.fill(30)("Steak Sandwich")) should be(155)
  }

  test("Cold food service charge does not have a cap") {
    Bill.calcBill(List.fill(200)("Cheese Sandwich")) should be(440)
  }

  test("Cold food service rate should not override Hot Food service rate if it comes after") {
    Bill.calcBill(List("Steak Sandwich", "Cheese Sandwich")) should be(7.80)
  }

  test("Bill total of order contains premium item should have 25% service charge") {
    Bill.calcBill(List("Cola", "Coffee", "Cheese Sandwich", "Steak Sandwich", "Lobster")) should be(41.25)
  }

  test("Premium food service charge should be capped at $40") {
    Bill.calcBill(List.fill(200)("Lobster")) should be(5040)
  }

}
