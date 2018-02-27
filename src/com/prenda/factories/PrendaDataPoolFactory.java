package com.prenda.factories;

import com.felees.hbnpojogen.randomlib.data.dataGeneration.*;
import com.prenda.model.obj.*;

import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;

/** 
 * DAO factory implementation.
 * @author autogenerated
 */
@Component
public class PrendaDataPoolFactory {
	/** Table commit order. */
    private static final Multimap<String, String> tableDeps = ArrayListMultimap.create();
	/** DB commit order. */
	private static final String[] commitOrder = new String[]{"Users", "Redeem", "Pullout", "Pawn", "Page", "Level", "Ledger", "Journal", "Interest", "Genkey", "Customer", "Branch", "Accounts"};
	static{
		// Store table deps for possible use. 
	}

    /**
     * Data pool factory for Accounts.
     * @return AccountsA Accounts object
     */
    public static Accounts getAccounts() {

        Accounts accounts = new Accounts();    
        
        accounts.setAccountcode(BasicDataGenerator.generateRandomInt());
        accounts.setAccountname(BasicDataGenerator.generateRandomString(30));
        accounts.setId((byte)(BasicDataGenerator.generateRandomTinyInt()));

        return accounts;
    }

    /**
     * Data pool factory for Branch.
     * @return BranchA Branch object
     */
    public static Branch getBranch() {

        Branch branch = new Branch();    
        
        branch.setAddress(BasicDataGenerator.generateRandomString(100));
        branch.setAdvanceInterest(BasicDataGenerator.generateRandomDouble());
        branch.setArchive(BasicDataGenerator.generateRandomBoolean());
        branch.setBalance(BasicDataGenerator.generateRandomDouble());
        branch.setCounter(BasicDataGenerator.generateRandomLong());
        branch.setExtend((byte)(BasicDataGenerator.generateRandomTinyInt()));
        branch.setName(BasicDataGenerator.generateRandomString(50));
        branch.setOwner((byte)(BasicDataGenerator.generateRandomTinyInt()));
        branch.setPtNumber(BasicDataGenerator.generateRandomLong());
        branch.setReserve((byte)(BasicDataGenerator.generateRandomTinyInt()));
        branch.setServiceCharge(BasicDataGenerator.generateRandomDouble());

        return branch;
    }

    /**
     * Data pool factory for Customer.
     * @return CustomerA Customer object
     */
    public static Customer getCustomer() {

        Customer customer = new Customer();    
        
        customer.setAddress(BasicDataGenerator.generateRandomString(60));
        customer.setArchive(BasicDataGenerator.generateRandomBoolean());
        customer.setFirstName(BasicDataGenerator.generateRandomString(20));
        customer.setLastName(BasicDataGenerator.generateRandomString(20));
        customer.setMiddleName(BasicDataGenerator.generateRandomString(20));

        return customer;
    }

    /**
     * Data pool factory for Genkey.
     * @return GenkeyA Genkey object
     */
    public static Genkey getGenkey() {

        Genkey genkey = new Genkey();    
        
        genkey.setId(BasicDataGenerator.generateRandomLong());
        genkey.setPassword(BasicDataGenerator.generateRandomStringChar(10));

        return genkey;
    }

    /**
     * Data pool factory for Interest.
     * @return InterestA Interest object
     */
    public static Interest getInterest() {

        Interest interest = new Interest(); 
        InterestPK interestPK = new InterestPK();
        Branch branch = new Branch();
        
        interestPK.setDay((byte)(BasicDataGenerator.generateRandomTinyInt()));
        branch.setId((byte)(BasicDataGenerator.generateRandomTinyInt()));
        interestPK.setInterestid(branch);
        
        interest.setInterestPK(interestPK);
        interest.setRate((byte)(BasicDataGenerator.generateRandomTinyInt()));

        return interest;
    }

    /**
     * Data pool factory for Journal.
     * @return JournalA Journal object
     */
    public static Journal getJournal() {

        Journal journal = new Journal();    
        
        journal.setAccountid((byte)(BasicDataGenerator.generateRandomTinyInt()));
        journal.setAmount(BasicDataGenerator.generateRandomDouble());
        journal.setBranchid((byte)(BasicDataGenerator.generateRandomTinyInt()));
        journal.setDescription(BasicDataGenerator.generateRandomString(100));
        journal.setJournalDate(BasicDataGenerator.generateDate());
        journal.setJournalGroup(BasicDataGenerator.generateRandomString(18));

        return journal;
    }

    /**
     * Data pool factory for Ledger.
     * @return LedgerA Ledger object
     */
    public static Ledger getLedger() {

        Ledger ledger = new Ledger();    
        
        ledger.setEncoder(BasicDataGenerator.generateRandomString(20));
        ledger.setId(BasicDataGenerator.generateRandomLong());
        ledger.setLedgerDate(BasicDataGenerator.generateDate());

        return ledger;
    }

    /**
     * Data pool factory for Level.
     * @return LevelA Level object
     */
    public static Level getLevel() {

        Level level = new Level();    
        
        level.setDescription(BasicDataGenerator.generateRandomString(20));
        level.setId((byte)(BasicDataGenerator.generateRandomTinyInt()));

        return level;
    }

    /**
     * Data pool factory for Page.
     * @return PageA Page object
     */
    public static Page getPage() {

        Page page = new Page();    
        
        page.setAuction((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setCustomer((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setForeclose((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setId(BasicDataGenerator.generateRandomLong());
        page.setInventory((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setOutstanding((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setPawn((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setPullout((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setRedeem((byte)(BasicDataGenerator.generateRandomTinyInt()));
        page.setUser((byte)(BasicDataGenerator.generateRandomTinyInt()));

        return page;
    }

    /**
     * Data pool factory for Pawn.
     * @return PawnA Pawn object
     */
    public static Pawn getPawn() {

        Pawn pawn = new Pawn();    
        
        pawn.setAdvanceInterest(BasicDataGenerator.generateRandomDouble());
        pawn.setAppraised(BasicDataGenerator.generateRandomDouble());
        pawn.setBcode((byte)(BasicDataGenerator.generateRandomTinyInt()));
        pawn.setBpid(BasicDataGenerator.generateRandomLong());
        pawn.setBranch(BasicDataGenerator.generateRandomLong());
        pawn.setCreateDate(BasicDataGenerator.generateDate());
        pawn.setDescription(BasicDataGenerator.generateRandomString(255));
        pawn.setEncoder(BasicDataGenerator.generateRandomString(50));
        pawn.setExtend((byte)(BasicDataGenerator.generateRandomTinyInt()));
        pawn.setLoan(BasicDataGenerator.generateRandomDouble());
        pawn.setLoanDate(BasicDataGenerator.generateDate());
        pawn.setNameid(BasicDataGenerator.generateRandomLong());
        pawn.setPt(BasicDataGenerator.generateRandomLong());
        pawn.setSerial(BasicDataGenerator.generateRandomLong());
        pawn.setServiceCharge(BasicDataGenerator.generateRandomDouble());

        return pawn;
    }

    /**
     * Data pool factory for Pullout.
     * @return PulloutA Pullout object
     */
    public static Pullout getPullout() {

        Pullout pullout = new Pullout();    
        
        pullout.setAuction(BasicDataGenerator.generateRandomBoolean());
        pullout.setCreateDate(BasicDataGenerator.generateDate());
        pullout.setEncoder(BasicDataGenerator.generateRandomString(50));
        pullout.setId(BasicDataGenerator.generateRandomLong());
        pullout.setPulloutDate(BasicDataGenerator.generateDate());
        pullout.setUsername(BasicDataGenerator.generateRandomString(50));

        return pullout;
    }

    /**
     * Data pool factory for Redeem.
     * @return RedeemA Redeem object
     */
    public static Redeem getRedeem() {

        Redeem redeem = new Redeem();    
        
        redeem.setCreateDate(BasicDataGenerator.generateDate());
        redeem.setEncoder(BasicDataGenerator.generateRandomString(50));
        redeem.setId(BasicDataGenerator.generateRandomLong());
        redeem.setInterest(BasicDataGenerator.generateRandomDouble());
        redeem.setRedeemDate(BasicDataGenerator.generateDate());

        return redeem;
    }

    /**
     * Data pool factory for Users.
     * @return UsersA Users object
     */
    public static Users getUsers() {

        Users users = new Users();    
        
        users.setArchive(BasicDataGenerator.generateRandomBoolean());
        users.setBranch(new Branch(BasicDataGenerator.generateRandomByte()));
        users.setFirstname(BasicDataGenerator.generateRandomString(50));
        users.setLastname(BasicDataGenerator.generateRandomString(50));
        users.setLevel((byte)(BasicDataGenerator.generateRandomTinyInt()));
        users.setLoanDate(BasicDataGenerator.generateDate());
        users.setMi(BasicDataGenerator.generateRandomString(2));
        users.setPassword(BasicDataGenerator.generateRandomString(50));
        users.setUsername(BasicDataGenerator.generateRandomString(50));

        return users;
    }
    /** Returns the commit order of this database. 
	 * Useful for iterating through the classes for deletion in the right order. 
	 * @return String[] list of classes
	 */
	public static String[] getDBCommitOrder() {
		return commitOrder;
	}
	
	/**
	 * @param className classname to return
	 * @return the tabledeps for the given class name
	 */
	public static Collection<String> getTabledeps(String className) {
		return tableDeps.get(className);
	}
	
}