using System.Collections.Generic;
using System.Linq;
using Lista9.Models;

namespace Lista9.DataContext
{
    public class ArticleList : IArticleContext
    {
        private int nextId = 0;
        private List<Article> articles = new List<Article>();

        public IEnumerable<Article> GetArticles()
        {
            return articles;
        }
        public Article GetArticle(int id)
        {
            return articles.FirstOrDefault(a => a.Id == id);
        }
        public void AddArticle(Article article)
        {
            article.Id = nextId++;
            articles.Add(article);
        }
        public void RemoveArticle(int id)
        {
            Article articleToRemove = articles.FirstOrDefault(a => a.Id == id);
            if (articleToRemove != null)
                articles.Remove(articleToRemove);
        }
        public void UpdateArticle(Article article)
        {
            Article articleToUpdate = articles.FirstOrDefault(a => a.Id == article.Id);
            articles = articles.Select(a => (a.Id == article.Id) ? article : a).ToList();
        }

    }
}
