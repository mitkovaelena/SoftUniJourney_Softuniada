package correctness;

import chainblock.Chainblock;
import chainblock.IChainblock;
import chainblock.Transaction;
import chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test41 {
    @Test
    public void GetByRceiver_ShouldReturnCorrectRange_CorrectlyOrdered()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        Transaction tx1 = new Transaction(2, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new Transaction(1, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx3 = new Transaction(4, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx4 = new Transaction(3, TransactionStatus.Successfull, "joro", "pesho", 15.6);
        Transaction tx5 = new Transaction(8, TransactionStatus.Failed, "joro", "pesho", 17.8);
        Transaction[] expected = new Transaction[]
                {
                        tx5, tx4, tx3, tx2, tx1
                };
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        //Assert
        ArrayList<Transaction> list = new ArrayList<Transaction>();
        Iterable<Transaction> res = cb.getByReceiverAndAmountRange("pesho", 0, 20);
        for(Transaction tx : res){
            list.add(tx);
        }
        Transaction[] actual = (Transaction[])list.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

}
