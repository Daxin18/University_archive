using System.Collections.Generic;
using Lista9.Models;

namespace Lista9.DataContext
{
    public class ArticleDictionary: IArticleContext
    {
        private int nextId = 0;
        private Dictionary<int, Article> articles = new Dictionary<int, Article>();

        public IEnumerable<Article> GetArticles()
        {
            return articles.Values;
        }
        public Article GetArticle(int id)
        {
            return articles.GetValueOrDefault(id);
        }
        public void AddArticle(Article article)
        {
            article.Id = nextId++;
            articles.Add(article.Id, article);
        }
        public void RemoveArticle(int id)
        {
            if (articles.ContainsKey(id))
                articles.Remove(id);
        }
        public void UpdateArticle(Article article)
        {
            if (articles.ContainsKey(article.Id))
                articles[article.Id] = article;
        }
    }
}
