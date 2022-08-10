package com.example.tugas_teori8_hibernate_2072007.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Watchlist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idWatchList")
    private int idWatchList;
    @Basic
    @Column(name = "LastWatch")
    private Integer lastWatch;
    @Basic
    @Column(name = "Favorite")
    private Byte favorite;
    @ManyToOne
    @JoinColumn(name = "Movie_idMovie", referencedColumnName = "idMovie", nullable = false)
    private Movie movieByMovieIdMovie;
    @ManyToOne
    @JoinColumn(name = "User_idUser", referencedColumnName = "idUser", nullable = false)
    private User userByUserIdUser;

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public Integer getLastWatch() {
        return lastWatch;
    }

    public void setLastWatch(Integer lastWatch) {
        this.lastWatch = lastWatch;
    }

    public Byte getFavorite() {
        return favorite;
    }

    public void setFavorite(Byte favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watchlist watchlist = (Watchlist) o;
        return idWatchList == watchlist.idWatchList && Objects.equals(lastWatch, watchlist.lastWatch) && Objects.equals(favorite, watchlist.favorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWatchList, lastWatch, favorite);
    }

    public Movie getMovieByMovieIdMovie() {
        return movieByMovieIdMovie;
    }

    public void setMovieByMovieIdMovie(Movie movieByMovieIdMovie) {
        this.movieByMovieIdMovie = movieByMovieIdMovie;
    }

    public User getUserByUserIdUser() {
        return userByUserIdUser;
    }

    public void setUserByUserIdUser(User userByUserIdUser) {
        this.userByUserIdUser = userByUserIdUser;
    }

    public String getTrueOrFalse() {
        if (favorite == 1) {
            return "True";
        } else {
            return "False";
        }
    }

    public String getDurationAndLastWatch(){
        return lastWatch + "/" + movieByMovieIdMovie.getDurasi();
    }
}
