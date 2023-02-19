using System.Collections.Generic;
using Lista9.Models;

namespace Lista9.DataContext
{
    public interface IArticleContext
    {
        IEnumerable<Article> GetArticles();
        Article GetArticle(int id);
        void AddArticle(Article article);
        void RemoveArticle(int id);
        void UpdateArticle(Article article);

    }
}
