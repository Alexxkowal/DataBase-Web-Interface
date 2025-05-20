package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query(value = "SELECT * FROM staff s " +
            "WHERE (:name IS NULL OR s.name ILIKE '%' || :name || '%') " +
            "AND (:position IS NULL OR s.position = :position) " +
            "AND (:minSalary IS NULL OR s.salary >= :minSalary) " +
            "AND (:maxSalary IS NULL OR s.salary <= :maxSalary)",
            countQuery = "SELECT count(*) FROM staff s " +
                    "WHERE (:name IS NULL OR s.name ILIKE '%' || :name || '%') " +
                    "AND (:position IS NULL OR s.position = :position) " +
                    "AND (:minSalary IS NULL OR s.salary >= :minSalary) " +
                    "AND (:maxSalary IS NULL OR s.salary <= :maxSalary)",
            nativeQuery = true)
    Page<Staff> searchStaff(
            @Param("name") String name,
            @Param("position") String position,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary,
            Pageable pageable);

    @Modifying
    @Query(value = "CALL increase_salary_by_position(:position, CAST(:percent AS DECIMAL(5,2)))", nativeQuery = true)
    void increaseSalaryByPosition(@Param("position") String position, @Param("percent") Double percent);
}
