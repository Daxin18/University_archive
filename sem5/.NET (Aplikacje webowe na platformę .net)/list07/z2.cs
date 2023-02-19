using LinqExamples;
using System.Collections.Generic;
using System.Linq;

namespace lab7
{
    class z2
    {
        //a
        public static IEnumerable<string> topicsSorted(IEnumerable<StudentWithTopics> students)
        {
            return students
                .SelectMany(s => s.Topics)
                .GroupBy(topic => topic)
                .OrderByDescending(topic => topic.Count())
                .Select(topic => topic.Key);
        }

        //b
        public static IEnumerable<(Gender, string[])> topicsSortedByGender(IEnumerable<StudentWithTopics> students)
        {
            return students
                .GroupBy(s => s.Gender)
                .Select(g => (
                    g.Key,
                    g.SelectMany(s => s.Topics)
                        .GroupBy(topic => topic)
                        .OrderByDescending(topic => topic.Count())
                        .Select(topic => topic.Key).ToArray()
                ));
        }
    }
}
