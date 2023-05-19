package com.example.userdiscount.repository;

import com.example.userdiscount.domain.models.Discount;
import com.example.userdiscount.domain.models.DiscountID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, DiscountID> {
}
