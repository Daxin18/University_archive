using LinqExamples;
using System.Collections.Generic;
using System;

namespace lab7
{
    class main
    {
        static void Main(string[] args)
        {
            var students = Generator.GenerateStudentsWithTopicsEasy();
            int n = 3;

            CheckZ1(students, n);
            CheckZ2a(students);
            CheckZ2b(students);
            CheckZ3(students);
            CheckZ4();
            CheckZ3b();

        }

        static void CheckZ3b()
        {
            var topics = Topic.GenerateTopicsQuery();

            foreach (Topic topic in topics)
                Console.WriteLine(topic);
        }

        static void CheckZ1(List<StudentWithTopics> students, int n)
        {
            var result = z1.putStudentsInGroups(students, n);

            var i = 1;
            foreach (StudentWithTopics[] group in result)
            {
                Console.WriteLine($"Group {i++}:");
                foreach (StudentWithTopics student in group)
                {
                    Console.WriteLine($"\t{student.ToString()}");
                }
            }
        }

        static void CheckZ2a(List<StudentWithTopics> students)
        {
            var result = z2.topicsSorted(students);

            foreach (string topic in result)
            {
                Console.WriteLine(topic);
            }
        }

        static void CheckZ2b(List<StudentWithTopics> students)
        {
            var result = z2.topicsSortedByGender(students);

            foreach ((Gender gender, string[] topics )in result)
            {
                Console.WriteLine(gender);
                foreach(string topic in topics)
                {
                    Console.WriteLine($"\t{topic}");
                }    
            }
        }

        static void CheckZ3(List<StudentWithTopics> students)
        {
            var result = z3.transformStudents(students);

            foreach (Student stud in result)
            {
                Console.WriteLine(stud.ToString());
            }
        }

        static void CheckZ4()
        {
            z4.reflectionDemo();
        }
    }
}