package com.example.cadproduto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cadproduto.BDHelper.ProdutosBd;
import com.example.cadproduto.model.Produtos;

public class formulario_produtos extends AppCompatActivity {

    EditText editText_NomeProduto, editText_Descricao, editText_Quantidade;
    Button btn_Polimorfi;
    Produtos editarProduto, produto;
    ProdutosBd bdHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);

        produto = new Produtos();

        bdHelper = new ProdutosBd(formulario_produtos.this);

        Intent intent = getIntent();

        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        editText_NomeProduto = (EditText) findViewById(R.id.editText_NomeProduto);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Quantidade = (EditText) findViewById(R.id.editText_Quantidade);

        btn_Polimorfi = (Button) findViewById(R.id.btn_Polimorfi);

        if(editarProduto != null){

            btn_Polimorfi.setText("Modificar");

            editText_NomeProduto.setText(editarProduto.getNomeProduto());
            editText_Descricao.setText(editarProduto.getDescricao());
            editText_Quantidade.setText(editarProduto.getQuantidade()+"");

            produto.setId(editarProduto.getId());
        } else {

            btn_Polimorfi.setText("Cadastrar Novo Produto");

        }

        btn_Polimorfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                produto.setNomeProduto(editText_NomeProduto.getText().toString());
                produto.setDescricao(editText_Descricao.getText().toString());
                produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

                if(btn_Polimorfi.getText().toString().equals("Cadastrar Novo Produto")){

                    bdHelper.salvarProdutos(produto);
                    bdHelper.close();

                } else {

                    bdHelper.alterarProduto(produto);
                    bdHelper.close();

                }

                Intent intent = new Intent(formulario_produtos.this, MainActivity.class);
                startActivity(intent);



            }
        });
    }
}
