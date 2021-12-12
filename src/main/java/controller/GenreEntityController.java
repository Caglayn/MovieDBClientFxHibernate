package controller;

import model.GenreEntity;
import model.GenreType;
import org.hibernate.Session;
import utils.LogUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;

public class GenreEntityController implements Controllable<GenreEntity> {

    @Override
    public void delete(GenreEntity entity) {
        try {
            GenreEntity tempEntity = find(entity.getGenreId());
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
    public void update(GenreEntity entity) {
        try {
            GenreEntity tempEntity = find(entity.getGenreId());
            tempEntity.setGenre(entity.getGenre());
            tempEntity.setMovies(entity.getMovies());

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
    public ArrayList<GenreEntity> list() {
        Session session = databaseConnectionHibernate();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> criteria = builder.createQuery(GenreEntity.class);
        criteria.from(GenreEntity.class);

        ArrayList<GenreEntity> genres = (ArrayList<GenreEntity>) session.createQuery(criteria).getResultList();
        if (genres.size() > 0) {
            LogUtil.getInstance().logInfo("Kayitlar bulundu.");
        } else
            LogUtil.getInstance().logInfo("Listelenecek kayit bulunamadi !");

        return genres;
    }

    @Override
    public GenreEntity find(long id) {
        Session session = databaseConnectionHibernate();
        GenreEntity tempEntity = null;
        try {
            tempEntity = session.find(GenreEntity.class, id);
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

    public void insertAllGenresToDB(){
            this.createMulti(GenreType.getAllGenres());
    }
}
