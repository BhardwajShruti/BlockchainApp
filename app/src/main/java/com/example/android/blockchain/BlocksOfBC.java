package com.example.android.blockchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class BlocksOfBC extends AppCompatActivity {
    TextView bc_text;
    int devIDS = 0;
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    ArrayList<String> devicesName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Intent i= getIntent();
        devIDS = i.getIntExtra("devIDS",0);
        setContentView(R.layout.activity_blocks_of_bc);

        while(devIDS>0){
            int rem = devIDS%10;
            switch (rem){
                case 1: {
                    devicesName.add("Air Conditioner");
                    break;
                }
                case 2: {
                    devicesName.add("Refridgerator");

                    break;
                }case 3: {
                    devicesName.add("Telivision");
                    break;
                }
            }

            devIDS = devIDS/10;

        }



        bc_text = (TextView) findViewById(R.id.text_bc);

        String texttbd = showData1();
        bc_text.setText(texttbd);

    }
    private String showData1(){





        String tillNow  = "Trying to Mine block ..."+devicesName.get(0);
        Log.i("text_now",tillNow);
        addBlock(new Block(devicesName.get(0), "0"));

     int size = devicesName.size();
     for(int i =1;i<size;i++){
         tillNow += "\nTrying to Mine block ... "+devicesName.get(i);

         Log.i("text_now",tillNow);
         addBlock(new Block(devicesName.get(i), blockchain.get(blockchain.size()-1).hash));


     }
        tillNow +="\n\nBlockchain is Valid: " + isChainValid();
        Log.i("text_now",tillNow);
        String blockchainJson = StringUtil.getJson(blockchain);

        tillNow = tillNow+"\n\nThe block chain: \n";
        Log.i("text_now",tillNow);
        tillNow = tillNow+blockchainJson;
        Log.i("text_now",tillNow);
        return tillNow;

    }

    private String showData(){
        String tillNow  = "Trying to Mine block 1...";

        addBlock(new Block("Hi im the first block", "0"));

        tillNow += "Trying to Mine block 2... ";

        Log.i("text_now",tillNow);
        //bc_text.setText(tillNow);
        addBlock(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));


        //tillNow =(String) bc_text.getText();
        tillNow += "Trying to Mine block 3... ";

        Log.i("text_now",tillNow);
        //bc_text.setText(tillNow);
        addBlock(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));


        //tillNow =(String) bc_text.getText();
        tillNow +="\nBlockchain is Valid: " + isChainValid();

        Log.i("text_now",tillNow);
        //   bc_text.setText(tillNow);

        String blockchainJson = StringUtil.getJson(blockchain);
        // tillNow =(String) bc_text.getText();
        tillNow = tillNow+"\nThe block chain: ";

        tillNow = tillNow+blockchainJson;
        Log.i("text_now",tillNow);
        //bc_text.setText(tillNow);
        return tillNow;
    }


    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}

