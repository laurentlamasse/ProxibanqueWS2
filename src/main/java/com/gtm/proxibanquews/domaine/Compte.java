package com.gtm.proxibanquews.domaine;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Represente un Compte, cette classe est abstraite. Les classes CompteCourant
 * et CompteEpargne en heritent.<br />
 * Un compte possede les attributs suivants : <br />
 * - int id : cle primaire utilisee dans la base de donnees (generation automatique)<br />
 * - String numero<br />
 * - double solde<br />
 * - Client proprietaire : relation OneToOne<br />
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMPTE")
@DiscriminatorValue("compte")
@Table(name = "compte")
public class Compte {
	//PROPRIETES
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCompte;
	private String numeroCompte;
	private double solde;
	private int proprietaire;
	
	//CONSTRUCTEURS
	public Compte() {
	}

	public Compte(Compte compte) {
		this.setNumeroCompte(compte.getNumeroCompte());
		this.setSolde(compte.getSolde());
	}
	
	public Compte(String numeroCompte, double solde) {
		super();
		this.numeroCompte = numeroCompte;
		this.solde = solde;
	}

	//ACCESSEURS ET MUTATEURS
	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public int getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(int proprietaire) {
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "id=" + idCompte + ", numero=" + numeroCompte + ", solde=" + solde + ", proprietaire=" + proprietaire;
	}
}
