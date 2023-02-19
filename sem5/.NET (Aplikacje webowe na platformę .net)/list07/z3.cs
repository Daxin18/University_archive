using LinqExamples;
using System.Collections.Generic;
using System.Linq;
using System;

namespace lab7
{
    class z3
    {
        public static List<Student> transformStudents(List<StudentWithTopics> students)
        {
            return students
                .Select (s => new Student(
                                    s.Id,
                                    s.Index,
                                    s.Name,
                                    s.Gender,
                                    s.Active,
                                    s.DepartmentId,
                                    s.Topics.SelectMany(name => Student.topicList.Where(t => t.Name == name)
                                                                                 .Select(t => t.Id)
                                                       ).ToList()
                                         )
                        ).ToList();
        }
    }

    // ========================================================== Topic
    public class Topic
    {
        public int Id { get; set; }
        public string Name { get; set; }

        public Topic(int id, string name)
        {
            this.Id = id;
            this.Name = name;
        }

        public static List<Topic> GenerateTopicsEasy()
        {
            return new List<Topic>() {
                new Topic(0, "C#"),
                new Topic(1, "algorithms"),
                new Topic(2, "Java"),
                new Topic(3, "PHP"),
                new Topic(4, "C++"),
                new Topic(5, "fuzzy logic"),
                new Topic(6, "Basic"),
                new Topic(7, "JavaScript"),
                new Topic(8, "neural networks"),
                new Topic(9, "web programming")
            };
        }

        public static List<Topic> GenerateTopicsQuery()
        {
            var students = Generator.GenerateStudentsWithTopicsEasy();
            var i = 0;
            return (students
                .SelectMany(s => s.Topics)
                .GroupBy(t => t)
                .Select(t => new Topic(i++, t.Key))).ToList();
        }

        public override string ToString()
        {
            return Id + ": " + Name;
        }
        public void printTopic()
        {
            Console.WriteLine(ToString());
        }
        public void printThisTopicAndGivenTopicsWithIntervalOf(List<Topic> topics, int interval)
        {

            Console.WriteLine(ToString());
            Console.WriteLine();
            var i = 0;
            foreach (Topic topic in topics)
            {
                if (i++ % interval == 0)
                {
                    Console.WriteLine(topic.ToString());
                }
            }
        }

    }


    //============================================================================= Student
    public class Student
    {
        public readonly static List<Topic> topicList = Topic.GenerateTopicsEasy();
        
        public int Id { get; set; }
        public int Index { get; set; }
        public string Name { get; set; }
        public Gender Gender { get; set; }
        public bool Active { get; set; }
        public int DepartmentId { get; set; }

        public List<int> Topics { get; set; }
        public Student(int id, int index, string name, Gender gender, bool active,
            int departmentId, List<int> topics)
        {
            this.Id = id;
            this.Index = index;
            this.Name = name;
            this.Gender = gender;
            this.Active = active;
            this.DepartmentId = departmentId;
            this.Topics = topics;
        }

        public override string ToString()
        {
            var result = $"{Id,2}) {Index,5}, {Name,11}, {Gender,6},{(Active ? "active" : "no active"),9},{DepartmentId,2}, topics: ";
            foreach (var idx in Topics)
                result += idx + ", ";
            return result;
        }
    }
}
