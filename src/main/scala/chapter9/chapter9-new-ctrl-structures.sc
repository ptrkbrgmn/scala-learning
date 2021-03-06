import java.io.{File, PrintWriter}

def twice(op: Double => Double, x: Double): Double = op(op(x))

val f = (x: Double) => x * 2
twice(f, 5)

val g = (_:Double) + 1
twice(g, 5)

// The compiler has information that the first argument of twice is
// a function with type 'Double => Double', which allows for placeholder syntax
twice(_ / 23, 1.2)
// Could be written (x: Double) => x / 23

/***/

// With this utility method we reuse code for closing PrintWriter
// We can do different stuff with PrintWriter at differenct places in our
// code, but closing is done in withPrintWriter
def withPrintWriter(file: File, op: PrintWriter => Unit): Unit = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close()
  }
}

val file = new File("/home/pabe/dev/scala-learning/src/main/scala/chapter9/date.txt")
withPrintWriter(file, writer => writer.println(new java.util.Date()))
// Here we don't have to care about closing
// So it's impossible to forget to close the file. This technique is called the loan pattern

// In any method invocation in Scala that has exactly one argument you could
// use curly braces instead of parenthesis
println("helle")
// can be written
println { "hello " }

// Does not work when many arguments, then you need parenthesis

// Curried withPrintWriter
def withPrintWriterCurried(file: File)(op: PrintWriter => Unit): Unit = {
  val writer = new PrintWriter(file)
  try {
    op(writer)
  } finally {
    writer.close
  }
}

// You should think if the code below as a control structure like
// while(someCondition) {
//    doSomthing
// }
// it is actually two method invokations
// (file) is the the first argument list
// { writer => ...} is the second argument list
withPrintWriterCurried(file) {
  writer => writer.println(new java.util.Date())
}

// The code above it is equal to:
withPrintWriterCurried(file)(writer => writer.println(new java.util.Date()))


/***/

var assertEnabled = true

def myAssert(predicate: () => Boolean) = {
  if(assertEnabled &&  !predicate())
    throw new AssertionError()
}
myAssert(() => 5 > 3)

// A by-name type, in which the empty parameter list, (), is left out, is only allowed for parameters. There
// is no such thing as a by-name variable or a by-name field.
def myAssertByNameParam(predicate: => Boolean) = {
  if(assertEnabled &&  !predicate)
    throw new AssertionError()
}
myAssertByNameParam(5 < 5)