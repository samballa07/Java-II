package tests;

// (c) Ariel Liu and Larry Herman, 2020.  You are allowed to use this code
// yourself, but not to provide it to anyone else.

import quuly.Quuly;
import org.junit.*;
import static org.junit.Assert.*;

public class Project1SecretTests {

  // Tests creating two Quuly objects, to ensure their data doesn't conflict.
  @Test public void testSecret1() {
    Quuly quuly1= new Quuly(3);
    Quuly quuly2= new Quuly(3);

    quuly1.addStudent("Kathy Kangaroo", 1232);
    quuly1.addStudent("Peggy Penguin", 9000);
    quuly1.addStudent("Wally Walrus", 1111);
    quuly1.addStudent("Ginny Giraffe", 2222);
    quuly1.addStudent("Dolly Dolphin", 6543);

    quuly2.addStudent("Dolly Dolphin", 6543);
    quuly2.addStudent("Ginny Giraffe", 2222);
    quuly2.addStudent("Benice Bear", 1729);

    quuly1.addStudentToQueue(2222);
    quuly1.addStudentToQueue(1232);
    quuly1.addStudentToQueue(6543);

    quuly2.addStudentToQueue(2222);
    quuly2.addStudentToQueue(1729);

    // Dolly Dolphin should be in quuly 1 only.
    assertTrue(quuly1.isInQueue(6543));
    assertFalse(quuly2.isInQueue(6543));

    // Ginny Giraffe should be in quuly 1 and quuly 2.
    assertTrue(quuly1.isInQueue(2222));
    assertTrue(quuly2.isInQueue(2222));
  }

  // Tests trying to add a student to the queue (by calling
  // addStudentToQueue()) who already has been to office hours the maximum
  // number of times.
  @Test public void testSecret2() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    assertTrue(quuly.addStudentToQueue(1232));
    assertTrue(quuly.helpNextStudent());
    assertTrue(quuly.addStudentToQueue(1232));
    assertTrue(quuly.helpNextStudent());
    assertFalse(quuly.addStudentToQueue(1232));
    assertEquals(0, quuly.queueSize());
  }

  // Tests trying to add a student to the queue (by calling
  // addStudentToQueue()) who currently already in the queue.
  @Test public void testSecret3() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    assertTrue(quuly.addStudentToQueue(1232));
    assertFalse(quuly.addStudentToQueue(1232));
  }

  // Tests that passing in zero and a negative value to the parameter
  // maxVisits of the Quuly constructor causes the result to be as if 1 had
  // been passed in.
  @Test public void testSecret4() {
    Quuly quuly1= new Quuly(0);
    Quuly quuly2= new Quuly(-2);

    quuly1.addStudent("Dolly Dolphin", 1234);
    quuly2.addStudent("Holly Dolphin", 4567);

    assertTrue(quuly1.addStudentToQueue(1234));
    assertTrue(quuly2.addStudentToQueue(4567));
    assertFalse(quuly1.addStudentToQueue(1234));
    assertFalse(quuly2.addStudentToQueue(4567));
  }

  // Tests trying to add an unknown student to the queue
  // (by calling addStudentToQueue()) who's already in the queue.
  @Test public void testSecret5() {
    Quuly quuly1= new Quuly(3);

    quuly1.addStudent("Kathy Kangaroo", 1232);
    quuly1.addStudent("Peggy Penguin", 9000);
    quuly1.addStudent("Wally Walrus", 1111);
    quuly1.addStudent("Ginny Giraffe", 2222);
    quuly1.addStudent("Dolly Dolphin", 6543);

    assertTrue(quuly1.addStudentToQueue(1111));
    assertTrue(quuly1.addStudentToQueue(2222));
    assertTrue(quuly1.addStudentToQueue(1232));
    assertFalse(quuly1.addStudentToQueue(1111));
  }

  // Tests trying to add an unknown student to the queue (by calling
  // addStudentToQueue()) who's not in the course at all (they were not
  // added previously by addStudent()).
  @Test public void testSecret6() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    assertFalse(quuly.addStudentToQueue(32));
  }

  // Tests calling isInQueue(Integer) on a nonexistent student ID (not one
  // of any student who was added previously by addStudent()).
  @Test public void testSecret7() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    assertFalse(quuly.isInQueue(32));
  }

  // Tests calling both forms of isInQueue() when the queue is empty.
  @Test public void testSecret8() {
    Quuly quuly= new Quuly(2);

    assertFalse(quuly.isInQueue(32));
    assertEquals(0,quuly.isInQueue("Annie Cat"));
  }

  // Tests the basic operation of dropStudent().
  @Test public void testSecret9() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    assertTrue(quuly.dropStudent(1232));
    assertEquals(1, quuly.numStudents());
  }

  // Tests calling dropStudent() on the ID of a nonexistent student.
  @Test public void testSecret10() {
    Quuly quuly= new Quuly(2);

    assertFalse(quuly.dropStudent(1232));

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    assertFalse(quuly.dropStudent(32));
  }

  //  Tests calling dropStudent() on the ID of student who happens to be in
  //  the office hours queue at the time.
  @Test public void testSecret11() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    quuly.addStudentToQueue(1232);
    quuly.addStudentToQueue(9000);

    assertTrue(quuly.dropStudent(1232));
    assertEquals(1, quuly.queueSize());
  }

  // Tests calling dropStudent() to remove all students, and add some new
  // ones afterwards.
  @Test public void testSecret12() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    assertTrue(quuly.dropStudent(1232));
    assertTrue(quuly.dropStudent(9000));
    assertEquals(0, quuly.numStudents());

    assertTrue(quuly.addStudent("Peggy Platypus", 1232));
    assertTrue(quuly.addStudent("Kathy Koala", 9802));
    assertEquals(2, quuly.numStudents());
  }

  // Tests that methods are not using reference comparison to compare names.
  @Test public void testSecret13() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    quuly.addStudentToQueue(1232);
    assertEquals(1, quuly.isInQueue(new String("Kathy Kangaroo")));
  }

  // Tests that methods are not ignoring case or whitespace when comparing
  // names.
  @Test public void testSecret14() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy Penguin", 9000);

    quuly.addStudentToQueue(1232);
    assertEquals(1, quuly.isInQueue(new String("Kathy Kangaroo")));
    assertEquals(0, quuly.isInQueue(new String("KathyKangaroo")));
    assertEquals(0 ,quuly.isInQueue(new String("Kathy kangaroo")));
  }

  // Tests that methods are properly comparing names.
  @Test public void testSecret15() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);
    quuly.addStudent("Peggy", 9000);

    quuly.addStudentToQueue(1232);
    assertEquals(0, quuly.isInQueue(new String("Kathy")));
    assertEquals(0, quuly.isInQueue(new String("Peggyy")));
    assertEquals(0, quuly.isInQueue(new String("Peggy Penguin")));
  }

  // Tests calling various methods with null name parameters and ensuring
  // nothing changes.
  @Test public void testSecret16() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);

    assertFalse(quuly.addStudent(null, 1233));

    quuly.addStudent("Wally Walrus", 1111);
    quuly.addStudent("Ginny Giraffe", 2222);
    quuly.addStudent("Dolly Dolphin", 6543);

    assertEquals(4, quuly.numStudents());
  }

  // Tests calling various methods with null ID parameters and ensuring
  // nothing changes.
  @Test public void testSecret17() {
    Quuly quuly= new Quuly(2);

    quuly.addStudent("Kathy Kangaroo", 1232);

    assertFalse(quuly.addStudent("Apple Pie", null));

    quuly.addStudent("Wally Walrus", 1111);
    quuly.addStudent("Ginny Giraffe", 2222);
    quuly.addStudent("Dolly Dolphin", 6543);

    assertFalse(quuly.addStudentToQueue(null));
    assertEquals(4, quuly.numStudents());
    assertFalse(quuly.dropStudent(null));
    assertEquals(4, quuly.numStudents());
    assertEquals(-1, quuly.numTimesHelped(null));
  }

}
