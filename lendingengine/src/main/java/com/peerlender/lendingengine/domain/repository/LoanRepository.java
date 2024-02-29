package com.peerlender.lendingengine.domain.repository;

import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByBorrower (AppUsers borrower);
    List<Loan> findAllByLender (AppUsers lender);


}
