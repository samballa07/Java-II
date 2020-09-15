package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

/* Note that your student tests must be your OWN WORK.  You can use the
 * ideas from this class as a guide in writing your own student tests, but
 * copying the public tests and turning them in as your student tests (with
 * minimal or no changes) would be plagiarism.
 */

import quuly.Quuly;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests creating a Quuly object and calling numStudents() on it, before
  // any students are added.
  @Test public void testPublic1() {
    Quuly quuly= new Quuly(3);

    assertEquals(0, quuly.numStudents());
  }

  // Tests creating a Quuly object, adding some students to it, then calling
  // numStudents().
  @Test public void testPublic2() {
    Quuly quuly= new Quuly(5);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);
    quuly.addStudent("Wally Walrus", 1111);
    quuly.addStudent("Ginny Giraffe", 2222);
    quuly.addStudent("Dolly Dolphin", 6543);
    quuly.addStudent("Ellie Elephant", 9561);
    quuly.addStudent("Benice Bear", 1729);

    assertEquals(7, quuly.numStudents());
  }

  // Tests trying to add a student to a Quuly object whose name is an empty
  // string, which should not succeed.
  @Test public void testPublic3() {
    Quuly quuly= new Quuly(5);

    assertTrue(quuly.addStudent("Kathy Kangaroo", 1111));
    assertFalse(quuly.addStudent("", 2222));  // this one will fail
    assertTrue(quuly.addStudent("Wally Walrus", 3333));

    assertEquals(2, quuly.numStudents());
  }

  // Tests trying to add the same student to a Quuly object twice (their ID
  // is the same as that of a student already added).
  @Test public void testPublic4() {
    Quuly quuly= new Quuly(5);

    assertTrue(quuly.addStudent("Ginny Giraffe", 4444));
    assertTrue(quuly.addStudent("Dolly Dolphin", 3333));
    assertTrue(quuly.addStudent("Ellie Elephant", 2222));

    // this one should fail; note that Freddy Frog's name is different, but
    // his ID is the same as an existing student's
    assertFalse(quuly.addStudent("Freddy Frog", 3333));

    assertEquals(3, quuly.numStudents());
  }

  // Tests adding different students who have the same names to a Quuly
  // object, which should work fine.
  @Test public void testPublic5() {
    Quuly quuly= new Quuly(8);

    assertTrue(quuly.addStudent("Dolly Dolphin", 3210));
    assertTrue(quuly.addStudent("Laura Llama", 1234));
    assertTrue(quuly.addStudent("Rita Cheetah", 6789));

    assertTrue(quuly.addStudent("Laura Llama", 2345));
    assertTrue(quuly.addStudent("Laura Llama", 3456));

    assertEquals(5, quuly.numStudents());
  }

  // Tests calling addStudentToQueue() and queueSize().
  @Test public void testPublic6() {
    Quuly quuly= TestData.exampleQuuly();

    assertEquals(10, quuly.queueSize());
  }

  // Tests calling addStudentToQueue() with the ID of a nonexistent student.
  @Test public void testPublic7() {
    Quuly quuly= TestData.exampleQuuly();

    assertEquals(10, quuly.queueSize());
    assertFalse(quuly.addStudentToQueue(9090));
    assertEquals(10, quuly.queueSize());  // didn't change
  }

  // Tests the basic operation of isInQueue(Integer) after some students are
  // added to the queue by calling addStudentToQueue().
  @Test public void testPublic8() {
    Quuly quuly= TestData.exampleQuuly();

    assertTrue(quuly.isInQueue(6513));
    assertTrue(quuly.isInQueue(3333));
    assertTrue(quuly.isInQueue(8234));
    assertTrue(quuly.isInQueue(4444));
    assertTrue(quuly.isInQueue(9174));

    assertFalse(quuly.isInQueue(6716));
  }

  // Tests the basic operation of isInQueue(String) after some students are
  // added to the queue by calling addStudentToQueue().
  @Test public void testPublic9() {
    Quuly quuly= TestData.exampleQuuly();

    // these are all the students who are in the queue
    for (String name : Arrays.asList("Dolly Dolphin", "Ginny Giraffe",
                                     "Wally Walrus", "Kathy Kangaroo",
                                     "Myrtle Turtle", "Squidley Squid",
                                     "Sally Salamander", "Coby Cobra",
                                     "Robyn Robin", "Amy Amoeba"))
      assertEquals(1, quuly.isInQueue(name));
  }

  // Tests calling isInQueue(String) when different students in a Quuly
  // object have matching names.
  @Test public void testPublic10() {
    Quuly quuly= new Quuly(8);

    assertTrue(quuly.addStudent("Dolly Dolphin", 3210));
    assertTrue(quuly.addStudent("Laura Llama", 1234));
    assertTrue(quuly.addStudent("Laura Llama", 2345));
    assertTrue(quuly.addStudent("Rita Cheetah", 6789));
    assertTrue(quuly.addStudent("Laura Llama", 3456));
    assertTrue(quuly.addStudent("Rita Cheetah", 4343));

    for (Integer id : Arrays.asList(3210, 1234, 2345, 6789, 3456, 4343))
      quuly.addStudentToQueue(id);

    assertEquals(1, quuly.isInQueue("Dolly Dolphin"));
    assertEquals(2, quuly.isInQueue("Rita Cheetah"));
    assertEquals(3, quuly.isInQueue("Laura Llama"));
  }

  // Tests the basic operation of helpNextStudent().
  @Test public void testPublic11() {
    Quuly quuly= TestData.exampleQuuly();
    int i;

    for (i= 1; i <= 6; i++)
      assertTrue(quuly.helpNextStudent());

    assertEquals(4, quuly.queueSize());

    // these are the students who were removed from the queue
    for (Integer studentRemoved : Arrays.asList(4444, 8888, 6513,
                                                2254, 4674, 8234))
      assertFalse(quuly.isInQueue(studentRemoved));

    // these are the students who are still in the queue
    for (Integer studentRemaining : Arrays.asList(1243, 9638, 3333, 9174))
      assertTrue(quuly.isInQueue(studentRemaining));
  }

  // Tests calling helpNextStudent() when the queue is empty.
  @Test public void testPublic12() {
    Quuly quuly= new Quuly(10);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);
    quuly.addStudent("Wally Walrus", 1111);
    quuly.addStudent("Ginny Giraffe", 2222);
    quuly.addStudent("Dolly Dolphin", 6543);
    quuly.addStudent("Ellie Elephant", 9561);
    quuly.addStudent("Benice Bear", 1729);

    assertFalse(quuly.helpNextStudent());
  }

  // Tests the basic functionality of numStudentsHelped().
  @Test public void testPublic13() {
    Quuly quuly= new Quuly(4);

    quuly.addStudent("Ginny Giraffe", 4444);
    quuly.addStudent("Wally Walrus", 8888);
    quuly.addStudent("Dolly Dolphin", 1111);

    assertEquals(0, quuly.numStudentsHelped());

    quuly.addStudentToQueue(8888);
    quuly.helpNextStudent();
    assertEquals(1, quuly.numStudentsHelped());

    quuly.addStudentToQueue(1111);
    quuly.helpNextStudent();
    assertEquals(2, quuly.numStudentsHelped());
  }

  // Tests the basic functionality of numTimesHelped().
  @Test public void testPublic14() {
    Quuly quuly= new Quuly(4);

    quuly.addStudent("Ginny Giraffe", 4444);
    quuly.addStudent("Wally Walrus", 8888);
    quuly.addStudent("Dolly Dolphin", 3333);

    quuly.addStudentToQueue(8888);
    quuly.helpNextStudent();
    
    assertEquals(1, quuly.numTimesHelped(8888));
  }

  // Tests calling numTimesHelped() on a student who has come to office
  // hours more than once.
  @Test public void testPublic15() {
    Quuly quuly= new Quuly(100);

    quuly.addStudent("Oscar Ostrich", 3232);
    quuly.addStudent("Robyn Robin", 4674);
    quuly.addStudent("Hedwig Hedgehog", 6716);

    quuly.addStudentToQueue(4674);
    quuly.helpNextStudent();
    quuly.addStudentToQueue(4674);
    quuly.helpNextStudent();

    assertEquals(2, quuly.numTimesHelped(4674));
  }

  // Tests calling numTimesHelped() on a student who hasn't gone to office
  // hours at all.
  @Test public void testPublic16() {
    Quuly quuly= new Quuly(5);

    quuly.addStudent("Peggy Penguin", 8888);
    quuly.addStudent("Kathy Kangaroo", 3333);
    quuly.addStudent("Bernice Bear", 2222);

    assertEquals(0, quuly.numTimesHelped(3333));
  }

  // Tests calling numTimesHelped() on a nonexistent student.
  @Test public void testPublic17() {
    Quuly quuly= TestData.exampleQuuly();

    assertEquals(-1, quuly.numTimesHelped(1000));
  }

}
