package controller;

import model.MovieEntity;
import org.hibernate.Session;
import utils.LogUtil;
import utils.ReadFileUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class MovieEntityController implements Controllable<MovieEntity> {
    @Override
    public void delete(MovieEntity entity) {
        try {
            MovieEntity tempEntity = find(entity.getMovieId());
            if (tempEntity != null) {
                Session session = databaseConnectionHibernate();
                session.getTransaction().begin();
                session.remove(tempEntity);
                session.getTransaction().commit();
                LogUtil.getInstance().logInfo("Silme  islemi tamamlandi : >> " + tempEntity);
            }
        } catch (Exception e) {
            LogUtil.getInstance().logError("Silme sirasinda hata meydana geldi : >> " + this.getClass());
        }
    }

    @Override
    public void update(MovieEntity entity) {
        try {
            MovieEntity tempEntity = find(entity.getMovieId());
            tempEntity.setTitle(entity.getTitle());
            tempEntity.setLink(entity.getLink());
            tempEntity.setGenres(entity.getGenres());
            tempEntity.setRatings(entity.getRatings());
            tempEntity.setTags(entity.getTags());

            Session session = databaseConnectionHibernate();
            session.getTransaction().begin();
            session.merge(tempEntity);
            session.getTransaction().commit();
            LogUtil.getInstance().logInfo("Guncelleme islemi tamamlandi : >> " + tempEntity);
        } catch (Exception e) {
            LogUtil.getInstance().logError("Guncelleme sirasinda hata meydana geldi : >> " + this.getClass());
        }
    }

    @Override
    public ArrayList<MovieEntity> list() {
        Session session = databaseConnectionHibernate();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MovieEntity> criteria = builder.createQuery(MovieEntity.class);
        criteria.from(MovieEntity.class);

        ArrayList<MovieEntity> movies = (ArrayList<MovieEntity>) session.createQuery(criteria).getResultList();
        if (movies.size() > 0) {
            LogUtil.getInstance().logInfo("Kayitlar bulundu.");
        } else
            LogUtil.getInstance().logInfo("Listelenecek kayit bulunamadi !");

        return movies;
    }

    @Override
    public MovieEntity find(long id) {
        Session session = databaseConnectionHibernate();
        MovieEntity tempEntity = null;
        try {
            tempEntity = session.find(MovieEntity.class, id);
            if (tempEntity != null) {
                LogUtil.getInstance().logInfo("Kayit bulundu : >> " + tempEntity);
            } else {
                LogUtil.getInstance().logInfo("Aradiginiz kriterde kayit bulunamadi !!");
            }
        } catch (Exception e) {
            LogUtil.getInstance().logError("Arama sirasinda hata meydana geldi : >> " + this.getClass());
        }
        return tempEntity;
    }

    public void readFromFileAndWriteToDBAllMovies(){
        TreeMap<Long, MovieEntity> movies = ReadFileUtil.getInstance().constructAllMovies();
        this.createMulti(new ArrayList<>(movies.values()));
        LogUtil.getInstance().logInfo("Tum veriler basarili bir sekilde olusturuldu.. !!");
    }
}
