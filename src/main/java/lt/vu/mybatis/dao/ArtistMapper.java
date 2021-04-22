package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Artist;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ArtistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Tue May 12 15:59:51 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Tue May 12 15:59:51 EEST 2020
     */
    int insert(Artist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Tue May 12 15:59:51 EEST 2020
     */
    Artist selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Tue May 12 15:59:51 EEST 2020
     */
    List<Artist> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.ARTIST
     *
     * @mbg.generated Tue May 12 15:59:51 EEST 2020
     */
    int updateByPrimaryKey(Artist record);
}