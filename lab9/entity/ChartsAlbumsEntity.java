package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "charts_albums", schema = "musicalbums")
public class ChartsAlbumsEntity {
    private int id;
    private int chartId;
    private int albumId;
    private int rank;

    public ChartsAlbumsEntity() {
    }

    public ChartsAlbumsEntity(int chartId, int albumId, int rank) {
        this.chartId = chartId;
        this.albumId = albumId;
        this.rank = rank;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "chart_id")
    public int getChartId() {
        return chartId;
    }

    public void setChartId(int chartId) {
        this.chartId = chartId;
    }

    @Basic
    @Column(name = "album_id")
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartsAlbumsEntity that = (ChartsAlbumsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(chartId, that.chartId) &&
                Objects.equals(albumId, that.albumId) &&
                Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chartId, albumId, rank);
    }

    @Override
    public String toString() {
        return "ChartsAlbumsEntity{" +
                "id=" + id +
                ", chartId=" + chartId +
                ", albumId=" + albumId +
                ", rank=" + rank +
                '}';
    }
}
