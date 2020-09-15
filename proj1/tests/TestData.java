package tests;

import quuly.Quuly;

/* This class contains a utility method that creates and returns an example
 * Quuly objects that some of the public (and secret) tests can use, to
 * reduce the amount of code needed in different tests to create objects to
 * test the methods with.  You can use this method in your student tests as
 * well, but don't modify this class, because our version is going to be
 * used on the submit server.  (You can also write your own helper methods
 * like the one below in your StudentTests class, and you can add your own
 * classes to the tests package, if you want to use modified versions of
 * this method.)
 */

public class TestData {

  public static Quuly exampleQuuly() {
    Quuly quuly= new Quuly(7);

    quuly.addStudent("Dolly Dolphin", 3333);
    quuly.addStudent("Ginny Giraffe", 4444);
    quuly.addStudent("Wally Walrus", 8888);
    quuly.addStudent("Ellie Elephant", 9999);
    quuly.addStudent("Kathy Kangaroo", 1243);
    quuly.addStudent("Myrtle Turtle", 9638);
    quuly.addStudent("Squidley Squid", 9174);
    quuly.addStudent("Sally Salamander", 6513);
    quuly.addStudent("Coby Cobra", 2254);
    quuly.addStudent("Oscar Ostrich", 3232);
    quuly.addStudent("Robyn Robin", 4674);
    quuly.addStudent("Hedwig Hedgehog", 6716);
    quuly.addStudent("Amy Amoeba", 8234);

    quuly.addStudentToQueue(4444);
    quuly.addStudentToQueue(8888);
    quuly.addStudentToQueue(6513);
    quuly.addStudentToQueue(2254);
    quuly.addStudentToQueue(4674);
    quuly.addStudentToQueue(8234);
    quuly.addStudentToQueue(1243);
    quuly.addStudentToQueue(9638);
    quuly.addStudentToQueue(3333);
    quuly.addStudentToQueue(9174);

    return quuly;
  }

}
