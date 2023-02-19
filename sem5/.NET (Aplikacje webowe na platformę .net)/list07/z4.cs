using LinqExamples;
using System;
using System.Collections.Generic;
using System.Reflection;

namespace lab7
{
    class z4
    {
        public static void reflectionDemo()
        {
            Type topicType = Type.GetType("lab7.Topic");
            Type studentType = Type.GetType("lab7.Student");

            //a
            var topicInstance = Activator.CreateInstance(topicType, 10, ".NET");
            var studentInstance = Activator.CreateInstance(studentType, 1, 123456, "Name", Gender.Female, false, 4, new List<int>(){0, 1});

            //b
            MethodInfo info = topicType.GetMethod("printThisTopicAndGivenTopicsWithIntervalOf");
            info.Invoke(topicInstance, new Object[] { Topic.GenerateTopicsEasy(), 2 });

        }
    }
}
