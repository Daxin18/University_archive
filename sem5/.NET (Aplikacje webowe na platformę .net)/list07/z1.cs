using LinqExamples;
using System.Collections.Generic;
using System.Linq;

namespace lab7
{
    class z1
    {
        public static IEnumerable<StudentWithTopics[]> putStudentsInGroups(IEnumerable<StudentWithTopics> students,int n)
        {
            return students
                .OrderBy(s => s.Name)
                .ThenBy(s => s.Index)
                .Select((s, idx) => new { s, idx })
                .GroupBy(si => si.idx / n)
                .Select(group => group.Select(stud => stud.s).ToArray());
        }
    }
}
