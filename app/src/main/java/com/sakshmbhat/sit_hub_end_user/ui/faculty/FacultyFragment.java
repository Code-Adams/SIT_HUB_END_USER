package com.sakshmbhat.sit_hub_end_user.ui.faculty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView ISE,AD,CSE,ECE,ME,EIE,CE,IE,IEM,Maths,Physics,Chemistry,BT,MBA,Architecture,AandNT,NTech,MCA,Civil,EEE;

    private LinearLayout ISEnoData,NTechnoData,MCAnoData,CivilNoData,EEEnoData,CSEnoData,ADnoData,ECEnoData,MEnoData,EIEnoData,CEnoData,IEnoData,IEMnoData,MathsNoData,PhysicsNoData,ChemistryNoData,BTnoData,MBAnoData,ArchitectureNoData,AandNTnoData;

    private List<FacultyAttributes> ISE_list,NTech_list,MCA_list,Civil_list,EEE_list,AD_list,CSE_list,ECE_list,ME_list,EIE_list,CE_list,IE_list,IEM_list,Maths_list,Physics_list,Chemistry_list,BT_list,MBA_list,Architecture_list,AandNT_list;

    private DatabaseReference databaseReference,dbRef;

    private FacultyInfoAdapter recyclerAdapter;

    private TextView ISETitle,ADTitle,CSETitle,ECETitle,METitle,EIETitle,CETitle,IETitle,IEMTitle,MathsTitle,PhysTitle,ChemTitle,BTTitle,MBATitle,ArchitectureTitle,AandNTTitle,NTechTitle,MCATitle,CivilTitle,EEETitle;

    private TextView ISEVisitPage,ADVisitPage,CSEVisitPage,ECEVisitPage,MEVisitPage,EIEVisitPage,CEVisitPage,IEVisitPage,IEMVisitPage,MathsVisitPage,PhysVisitPage,ChemVisitPage,BTVisitPage,MBAVisitPage,ArchitectureVisitPage,AandNTVisitPage,NTechVisitPage,MCAVisitPage,CivilVisitPage,EEEVisitPage;

    private TextView ISEPageUrl,ADPageUrl,CSEPageUrl,ECEPageUrl,MEPageUrl,EIEPageUrl,CEPageUrl,IEPageUrl,IEMPageUrl,MathsPageUrl,PhysPageUrl,ChemPageUrl,BTPageUrl,MBAPageUrl,ArchitecturePageUrl,AandNTPageUrl,NTechPageUrl,MCAPageUrl,CivilPageUrl,EEEPageUrl;

    private LinearLayout ISEContent,ADContent,CSEContent,ECEContent,MEContent,EIEContent,CEContent,IEContent,IEMContent,MathsContent,PhysContent,ChemContent,BTContent,MBAContent,ArchitectureContent,AandNTContent,NTechContent,MCAContent,CivilContent,EEEContent;

    private boolean ISETap=false,ADTap=false,CSETap=false,ECETap=false,METap=false,EIETap=false,CETap=false,IETap=false,IEMTap=false,MathsTap=false,PhysTap=false,ChemTap=false,BTTap=false,MBATap=false,ArchitectureTap=false,AandNTTap=false,NTechTap=false,MCATap=false,CivilTap=false,EEETap=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        initialize(view);

        titleTapListeners();

        visitDeptPageListener();

        iseDepartment();//Done
        cseDepartment();//Done
        eceDepartment();//Done
        meDepartment();//Done
        CivilDepartment();//Done
        eieDepartment();
        ceDepartment();// Done
        //teDepartment();
        iemDepartment();//Done
        mathsDepartment();//Done
        physicsDepartment();//Done
        chemistryDepartment();//Done
        btDepartment();//Done
        mbaDepartment();//Done
        architectureDepartment();// Done
        adDepartment();//Done
        //ieDepartment();
        ntDepartment();//NON teaching and administration
        eeeDepartment();//Done
        mcaDepartment();//Done
        nanoTechDepartment();//Done

        return view;
    }


    private void visitDeptPageListener() {


        ISEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(ISEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        ADVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(ADPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        CSEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(CSEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        ECEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(ECEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        MEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(MEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        EIEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(EIEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        CEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(CEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        IEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(IEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        IEMVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(IEMPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        MathsVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(MathsPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        PhysVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(PhysPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        ChemVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(ChemPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        BTVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(BTPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        MBAVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(MBAPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        ArchitectureVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(ArchitecturePageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        AandNTVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(AandNTPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        NTechVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(NTechPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        MCAVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(MCAPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        CivilVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(CivilPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });
        EEEVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(EEEPageUrl.getText().toString()));
                getContext().startActivity(myWebLink);
            }
        });



    }

    private void titleTapListeners() {

        ISETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ISETap) {
                    ISEContent.setVisibility(View.GONE);
                    ISETap=false;
                }else{
                    ISEContent.setVisibility(View.VISIBLE);
                    ISETap=true;
                }
            }
        });
        ADTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ADTap) {
                    ADContent.setVisibility(View.GONE);
                    ADTap=false;
                }else{
                    ADContent.setVisibility(View.VISIBLE);
                    ADTap=true;
                }
            }
        });
        CSETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CSETap) {
                    CSEContent.setVisibility(View.GONE);
                    CSETap=false;
                }else{
                    CSEContent.setVisibility(View.VISIBLE);
                    CSETap=true;
                }
            }
        });
        ECETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ECETap) {
                    ECEContent.setVisibility(View.GONE);
                    ECETap=false;
                }else{
                    ECEContent.setVisibility(View.VISIBLE);
                    ECETap=true;
                }
            }
        });
        METitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (METap) {
                    MEContent.setVisibility(View.GONE);
                    METap=false;
                }else{
                    MEContent.setVisibility(View.VISIBLE);
                    METap=true;
                }
            }
        });
        EIETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EIETap) {
                    EIEContent.setVisibility(View.GONE);
                    EIETap=false;
                }else{
                    EIEContent.setVisibility(View.VISIBLE);
                    EIETap=true;
                }
            }
        });
        CETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CETap) {
                    CEContent.setVisibility(View.GONE);
                    CETap=false;
                }else{
                    CEContent.setVisibility(View.VISIBLE);
                    CETap=true;
                }
            }
        });
        IETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IETap) {
                    IEContent.setVisibility(View.GONE);
                    IETap=false;
                }else{
                    IEContent.setVisibility(View.VISIBLE);
                    IETap=true;
                }
            }
        });
        IEMTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IEMTap) {
                    IEMContent.setVisibility(View.GONE);
                    IEMTap=false;
                }else{
                    IEMContent.setVisibility(View.VISIBLE);
                    IEMTap=true;
                }
            }
        });
        MathsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MathsTap) {
                    MathsContent.setVisibility(View.GONE);
                    MathsTap=false;
                }else{
                    MathsContent.setVisibility(View.VISIBLE);
                    MathsTap=true;
                }
            }
        });
        PhysTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PhysTap) {
                    PhysContent.setVisibility(View.GONE);
                    PhysTap=false;
                }else{
                    PhysContent.setVisibility(View.VISIBLE);
                    PhysTap=true;
                }
            }
        });
        ChemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ChemTap) {
                    ChemContent.setVisibility(View.GONE);
                    ChemTap=false;
                }else{
                    ChemContent.setVisibility(View.VISIBLE);
                    ChemTap=true;
                }
            }
        });
        BTTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BTTap) {
                    BTContent.setVisibility(View.GONE);
                    BTTap=false;
                }else{
                    BTContent.setVisibility(View.VISIBLE);
                    BTTap=true;
                }
            }
        });
        MBATitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MBATap) {
                    MBAContent.setVisibility(View.GONE);
                    MBATap=false;
                }else{
                    MBAContent.setVisibility(View.VISIBLE);
                    MBATap=true;
                }
            }
        });
        ArchitectureTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ArchitectureTap) {
                    ArchitectureContent.setVisibility(View.GONE);
                    ArchitectureTap=false;
                }else{
                    ArchitectureContent.setVisibility(View.VISIBLE);
                    ArchitectureTap=true;
                }
            }
        });
        AandNTTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AandNTTap) {
                    AandNTContent.setVisibility(View.GONE);
                    AandNTTap=false;
                }else{
                    AandNTContent.setVisibility(View.VISIBLE);
                    AandNTTap=true;
                }
            }
        });
        NTechTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NTechTap) {
                    NTechContent.setVisibility(View.GONE);
                    NTechTap=false;
                }else{
                    NTechContent.setVisibility(View.VISIBLE);
                    NTechTap=true;
                }
            }
        });
        MCATitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MCATap) {
                    MCAContent.setVisibility(View.GONE);
                    MCATap=false;
                }else{
                    MCAContent.setVisibility(View.VISIBLE);
                    MCATap=true;
                }
            }
        });
        CivilTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CivilTap) {
                    CivilContent.setVisibility(View.GONE);
                    CivilTap=false;
                }else{
                    CivilContent.setVisibility(View.VISIBLE);
                    CivilTap=true;
                }
            }
        });
        EEETitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EEETap) {
                    EEEContent.setVisibility(View.GONE);
                    EEETap=false;
                }else{
                    EEEContent.setVisibility(View.VISIBLE);
                    EEETap=true;
                }
            }
        });

    }

    private void initialize(View view) {

        NTech=view.findViewById(R.id.NTechFacultyRecycler);
        MCA=view.findViewById(R.id.MCAFacultyRecycler);
        EEE=view.findViewById(R.id.EEEFacultyRecycler);
        AD=view.findViewById(R.id.ADFacultyRecycler);
        ISE=view.findViewById(R.id.ISEFacultyRecycler);
        CSE=view.findViewById(R.id.CSEFacultyRecycler);
        ECE=view.findViewById(R.id.ECEFacultyRecycler);
        ME=view.findViewById(R.id.MEFacultyRecycler);
        Civil=view.findViewById(R.id.CivilFacultyRecycler);
        EIE=view.findViewById(R.id.EIEFacultyRecycler);
        CE=view.findViewById(R.id.CEFacultyRecycler);
        IE=view.findViewById(R.id.IEFacultyRecycler);
        IEM=view.findViewById(R.id.IEMFacultyRecycler);
        Maths=view.findViewById(R.id.MathsFacultyRecycler);
        Physics=view.findViewById(R.id.PhyFacultyRecycler);
        Chemistry=view.findViewById(R.id.ChemFacultyRecycler);
        BT=view.findViewById(R.id.BioFacultyRecycler);
        MBA=view.findViewById(R.id.MBAFacultyRecycler);
        Architecture=view.findViewById(R.id.ArchFacultyRecycler);
        AandNT=view.findViewById(R.id.NTFacultyRecycler);


        NTechnoData=view.findViewById(R.id.NTechFacultyDataNotFound);
        MCAnoData=view.findViewById(R.id.MCAfacultyDataNotFound);
        EEEnoData=view.findViewById(R.id.EEEfacultyDataNotFound);
        ADnoData=view.findViewById(R.id.ADfacultyDataNotFound);
        ISEnoData=view.findViewById(R.id.ISEfacultyDataNotFound);
        CSEnoData=view.findViewById(R.id.CSEfacultyDataNotFound);
        ECEnoData=view.findViewById(R.id.ECEfacultyDataNotFound);
        MEnoData=view.findViewById(R.id.MEfacultyDataNotFound);
        CivilNoData=view.findViewById(R.id.CivilFacultyDataNotFound);
        EIEnoData=view.findViewById(R.id.EIEfacultyDataNotFound);
        CEnoData=view.findViewById(R.id.CEfacultyDataNotFound);
        IEnoData=view.findViewById(R.id.IEfacultyDataNotFound);
        IEMnoData=view.findViewById(R.id.IEMfacultyDataNotFound);
        MathsNoData=view.findViewById(R.id.MathsfacultyDataNotFound);
        PhysicsNoData=view.findViewById(R.id.PhyfacultyDataNotFound);
        ChemistryNoData=view.findViewById(R.id.ChemfacultyDataNotFound);
        BTnoData=view.findViewById(R.id.BiofacultyDataNotFound);
        MBAnoData=view.findViewById(R.id.MBAfacultyDataNotFound);
        ArchitectureNoData=view.findViewById(R.id.ArchfacultyDataNotFound);
        AandNTnoData=view.findViewById(R.id.NTfacultyDataNotFound);

        ISETitle=view.findViewById(R.id.ISETitle);
        ADTitle=view.findViewById(R.id.ADTitle);
        CSETitle=view.findViewById(R.id.CSETitle);
        ECETitle=view.findViewById(R.id.ECETitle);
        METitle=view.findViewById(R.id.METitle);
        EIETitle=view.findViewById(R.id.EIETitle);
        CETitle=view.findViewById(R.id.CETitle);
        IETitle=view.findViewById(R.id.IETitle);
        IEMTitle=view.findViewById(R.id.IEMTitle);
        MathsTitle=view.findViewById(R.id.MathsTitle);
        PhysTitle=view.findViewById(R.id.PhyTitle);
        ChemTitle=view.findViewById(R.id.ChemTitle);
        BTTitle=view.findViewById(R.id.BioTitle);
        MBATitle=view.findViewById(R.id.MBATitle);
        ArchitectureTitle=view.findViewById(R.id.ArchitectureTitle);
        AandNTTitle=view.findViewById(R.id.NTTitle);
        NTechTitle=view.findViewById(R.id.NTechTitle);
        MCATitle=view.findViewById(R.id.MCATitle);
        CivilTitle=view.findViewById(R.id.CivilTitle);
        EEETitle=view.findViewById(R.id.EEETitle);


        ISEVisitPage=view.findViewById(R.id.visitISEPage);
        ADVisitPage=view.findViewById(R.id.visitADPage);
        CSEVisitPage=view.findViewById(R.id.visitCSEPage);
        ECEVisitPage=view.findViewById(R.id.visitECEPage);
        MEVisitPage=view.findViewById(R.id.visitMEPage);
        EIEVisitPage=view.findViewById(R.id.visitEIEPage);
        CEVisitPage=view.findViewById(R.id.visitCEPage);
        IEVisitPage=view.findViewById(R.id.visitIEPage);
        IEMVisitPage=view.findViewById(R.id.visitIEMPage);
        MathsVisitPage=view.findViewById(R.id.visitMathsPage);
        PhysVisitPage=view.findViewById(R.id.visitPhyPage);
        ChemVisitPage=view.findViewById(R.id.visitChemPage);
        BTVisitPage=view.findViewById(R.id.visitBioPage);
        MBAVisitPage=view.findViewById(R.id.visitMBAPage);
        ArchitectureVisitPage=view.findViewById(R.id.visitArchitecturePage);
        AandNTVisitPage=view.findViewById(R.id.visitNTPage);
        NTechVisitPage=view.findViewById(R.id.visitNTechPage);
        MCAVisitPage=view.findViewById(R.id.visitMCAPage);
        CivilVisitPage=view.findViewById(R.id.visitCivilPage);
        EEEVisitPage=view.findViewById(R.id.visitEEEPage);

        ISEPageUrl=view.findViewById(R.id.ISEPageUrl);
        ADPageUrl=view.findViewById(R.id.ADPageUrl);
        CSEPageUrl=view.findViewById(R.id.CSEPageUrl);
        ECEPageUrl=view.findViewById(R.id.ECEPageUrl);
        MEPageUrl=view.findViewById(R.id.MEPageUrl);
        EIEPageUrl=view.findViewById(R.id.EIEPageUrl);
        CEPageUrl=view.findViewById(R.id.CEPageUrl);
        IEPageUrl=view.findViewById(R.id.IEPageUrl);
        IEMPageUrl=view.findViewById(R.id.IEMPageUrl);
        MathsPageUrl=view.findViewById(R.id.MathsPageUrl);
        PhysPageUrl=view.findViewById(R.id.PhyPageUrl);
        ChemPageUrl=view.findViewById(R.id.ChemPageUrl);
        BTPageUrl=view.findViewById(R.id.BioPageUrl);
        MBAPageUrl=view.findViewById(R.id.MBAPageUrl);
        ArchitecturePageUrl=view.findViewById(R.id.ArchitecturePageUrl);
        AandNTPageUrl=view.findViewById(R.id.NTPageUrl);
        NTechPageUrl=view.findViewById(R.id.NTechPageUrl);
        MCAPageUrl=view.findViewById(R.id.MCAPageUrl);
        CivilPageUrl=view.findViewById(R.id.CivilPageUrl);
        EEEPageUrl=view.findViewById(R.id.EEEPageUrl);

        ISEContent=view.findViewById(R.id.ISEContent);
        ADContent=view.findViewById(R.id.ADContent);
        CSEContent=view.findViewById(R.id.CSEContent);
        ECEContent=view.findViewById(R.id.ECEContent);
        MEContent=view.findViewById(R.id.MEContent);
        EIEContent=view.findViewById(R.id.EIEContent);
        CEContent=view.findViewById(R.id.CEContent);
        IEContent=view.findViewById(R.id.IEContent);
        IEMContent=view.findViewById(R.id.IEMContent);
        MathsContent=view.findViewById(R.id.MathsContent);
        PhysContent=view.findViewById(R.id.PhyContent);
        ChemContent=view.findViewById(R.id.ChemContent);
        BTContent=view.findViewById(R.id.BioContent);
        MBAContent=view.findViewById(R.id.MBAContent);
        ArchitectureContent=view.findViewById(R.id.ArchitectureContent);
        AandNTContent=view.findViewById(R.id.NTContent);
        NTechContent=view.findViewById(R.id.NTechContent);
        MCAContent=view.findViewById(R.id.MCAContent);
        CivilContent=view.findViewById(R.id.CivilContent);
        EEEContent=view.findViewById(R.id.EEEContent);



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Faculty");



    }

//    private void ieDepartment() {
//
//        dbRef=databaseReference.child("Administration and Non-teaching");
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                IE_list = new ArrayList<>();
//
//                if(!snapshot.exists()){
//
//                    IEnoData.setVisibility(View.VISIBLE);
//                    IE.setVisibility(View.GONE);
//                }else{
//
//                    IEnoData.setVisibility(View.GONE);
//                    IE.setVisibility(View.VISIBLE);
//
//                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//
//                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
//                        IE_list.add(attributes);
//
//                    }
//                    IE.setHasFixedSize(true);
//                    IE.setLayoutManager(new LinearLayoutManager(getContext()));
//                    recyclerAdapter = new FacultyInfoAdapter(IE_list, getContext(),"IE");
//                    IE.setAdapter(recyclerAdapter);
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }

    private void adDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    ADPageUrl.setText(snapshot.child("AiandDataScience").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "AD link unavailable", Toast.LENGTH_SHORT).show();

            }
        });

        dbRef=databaseReference.child("AD");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AD_list = new ArrayList<>();

                if(!snapshot.exists()){

                    ADnoData.setVisibility(View.VISIBLE);
                    AD.setVisibility(View.GONE);
                }else{

                    ADnoData.setVisibility(View.GONE);
                    AD.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        AD_list.add(attributes);

                    }
                    AD.setHasFixedSize(true);
                    AD.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(AD_list, getContext(),"AD");
                    AD.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void ntDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    AandNTPageUrl.setText(snapshot.child("AdminandNonTeaching").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "A&NT link unavailable", Toast.LENGTH_SHORT).show();

            }
        });

        dbRef=databaseReference.child("Administration and Non-teaching");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AandNT_list = new ArrayList<>();

                if(!snapshot.exists()){

                    AandNTnoData.setVisibility(View.VISIBLE);
                    AandNT.setVisibility(View.GONE);
                }else{

                    AandNTnoData.setVisibility(View.GONE);
                    AandNT.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        AandNT_list.add(attributes);

                    }
                    AandNT.setHasFixedSize(true);
                    AandNT.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(AandNT_list, getContext(),"Administration and Non-teaching");
                    AandNT.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void architectureDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    ArchitecturePageUrl.setText(snapshot.child("Architecture").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "Architecture link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("Architecture");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Architecture_list = new ArrayList<>();

                if(!snapshot.exists()){

                    ArchitectureNoData.setVisibility(View.VISIBLE);
                    Architecture.setVisibility(View.GONE);
                }else{

                    ArchitectureNoData.setVisibility(View.GONE);
                    Architecture.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        Architecture_list.add(attributes);

                    }
                    Architecture.setHasFixedSize(true);
                    Architecture.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(Architecture_list, getContext(),"Architecture");
                    Architecture.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void mbaDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    MBAPageUrl.setText(snapshot.child("MasterOfBusinessAdmin").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "MBA link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("MBA");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                MBA_list = new ArrayList<>();

                if(!snapshot.exists()){

                    MBAnoData.setVisibility(View.VISIBLE);
                    MBA.setVisibility(View.GONE);
                }else{

                    MBAnoData.setVisibility(View.GONE);
                    MBA.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        MBA_list.add(attributes);

                    }
                    MBA.setHasFixedSize(true);
                    MBA.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(MBA_list, getContext(),"MBA");
                    MBA.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void mcaDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    MCAPageUrl.setText(snapshot.child("MasterOfCompterApp").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "MCA link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("MCA");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                MCA_list = new ArrayList<>();

                if(!snapshot.exists()){

                    MCAnoData.setVisibility(View.VISIBLE);
                    MCA.setVisibility(View.GONE);
                }else{

                    MCAnoData.setVisibility(View.GONE);
                    MCA.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        MCA_list.add(attributes);

                    }
                    MCA.setHasFixedSize(true);
                    MCA.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(MCA_list, getContext(),"MCA");
                    MCA.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void btDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    BTPageUrl.setText(snapshot.child("Biotechnology").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "Bio-Tech link unavailable", Toast.LENGTH_SHORT).show();

            }
        });

        dbRef=databaseReference.child("BT");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                BT_list = new ArrayList<>();

                if(!snapshot.exists()){

                    BTnoData.setVisibility(View.VISIBLE);
                    BT.setVisibility(View.GONE);
                }else{

                    BTnoData.setVisibility(View.GONE);
                    BT.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        BT_list.add(attributes);

                    }
                    BT.setHasFixedSize(true);
                    BT.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(BT_list, getContext(),"BT");
                    BT.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void chemistryDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    ChemPageUrl.setText(snapshot.child("Chemistry").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "Chem link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Chemistry_list = new ArrayList<>();

                if(!snapshot.exists()){

                    ChemistryNoData.setVisibility(View.VISIBLE);
                    Chemistry.setVisibility(View.GONE);
                }else{

                    ChemistryNoData.setVisibility(View.GONE);
                    Chemistry.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        Chemistry_list.add(attributes);

                    }
                    Chemistry.setHasFixedSize(true);
                    Chemistry.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(Chemistry_list, getContext(),"Chemistry");
                    Chemistry.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void physicsDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    PhysPageUrl.setText(snapshot.child("Physics").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "Physics link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Physics_list = new ArrayList<>();

                if(!snapshot.exists()){

                    PhysicsNoData.setVisibility(View.VISIBLE);
                    Physics.setVisibility(View.GONE);
                }else{

                    PhysicsNoData.setVisibility(View.GONE);
                    Physics.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        Physics_list.add(attributes);

                    }
                    Physics.setHasFixedSize(true);
                    Physics.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(Physics_list, getContext(),"Physics");
                    Physics.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void mathsDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    MathsPageUrl.setText(snapshot.child("Mathematics").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "Maths link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("Maths");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Maths_list = new ArrayList<>();

                if(!snapshot.exists()){

                    MathsNoData.setVisibility(View.VISIBLE);
                    Maths.setVisibility(View.GONE);
                }else{

                    MathsNoData.setVisibility(View.GONE);
                    Maths.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        Maths_list.add(attributes);

                    }
                    Maths.setHasFixedSize(true);
                    Maths.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(Maths_list, getContext(),"Maths");
                    Maths.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void ceDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    CEPageUrl.setText(snapshot.child("ChemicalEngineering").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "CE link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("CE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CE_list = new ArrayList<>();

                if(!snapshot.exists()){

                    CEnoData.setVisibility(View.VISIBLE);
                    CE.setVisibility(View.GONE);
                }else{

                    CEnoData.setVisibility(View.GONE);
                    CE.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        CE_list.add(attributes);

                    }
                    CE.setHasFixedSize(true);
                    CE.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(CE_list, getContext(),"CE");
                    CE.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void eieDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    EIEPageUrl.setText(snapshot.child("ElectronicandInstrumentation").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "EIE link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("EIE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                EIE_list = new ArrayList<>();

                if(!snapshot.exists()){

                    EIEnoData.setVisibility(View.VISIBLE);
                    EIE.setVisibility(View.GONE);
                }else{

                    EIEnoData.setVisibility(View.GONE);
                    EIE.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        EIE_list.add(attributes);

                    }
                    EIE.setHasFixedSize(true);
                    EIE.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(EIE_list, getContext(),"EIE");
                    EIE.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void iemDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    IEMPageUrl.setText(snapshot.child("IeandManagement").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "IEM link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("IEM");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                IEM_list = new ArrayList<>();

                if(!snapshot.exists()){

                    IEMnoData.setVisibility(View.VISIBLE);
                    IEM.setVisibility(View.GONE);
                }else{

                    IEMnoData.setVisibility(View.GONE);
                    IEM.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        IEM_list.add(attributes);

                    }
                    IEM.setHasFixedSize(true);
                    IEM.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(IEM_list, getContext(),"IEM");
                    IEM.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void CivilDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    CivilPageUrl.setText(snapshot.child("CivilEngineering").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "Civil link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("Civil");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Civil_list = new ArrayList<>();

                if(!snapshot.exists()){

                    CivilNoData.setVisibility(View.VISIBLE);
                    Civil.setVisibility(View.GONE);
                }else{

                    CivilNoData.setVisibility(View.GONE);
                    Civil.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        Civil_list.add(attributes);

                    }
                    Civil.setHasFixedSize(true);
                    Civil.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(Civil_list, getContext(),"Civil");
                    Civil.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void meDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    MEPageUrl.setText(snapshot.child("MechanicalEngineering").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "ME link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("ME");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ME_list = new ArrayList<>();

                if(!snapshot.exists()){

                    MEnoData.setVisibility(View.VISIBLE);
                    ME.setVisibility(View.GONE);
                }else{

                    MEnoData.setVisibility(View.GONE);
                    ME.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        ME_list.add(attributes);

                    }
                    ME.setHasFixedSize(true);
                    ME.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(ME_list, getContext(),"ME");
                    ME.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void eceDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    ECEPageUrl.setText(snapshot.child("ElectronicAndCommunication").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "ECE link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("ECE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ECE_list = new ArrayList<>();

                if(!snapshot.exists()){

                    ECEnoData.setVisibility(View.VISIBLE);
                    ECE.setVisibility(View.GONE);
                }else{

                    ECEnoData.setVisibility(View.GONE);
                    ECE.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        ECE_list.add(attributes);

                    }
                    ECE.setHasFixedSize(true);
                    ECE.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(ECE_list, getContext(),"ECE");
                    ECE.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void eeeDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    EEEPageUrl.setText(snapshot.child("ElectronicandElectrical").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "EEE link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("EEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                EEE_list = new ArrayList<>();

                if(!snapshot.exists()){

                    EEEnoData.setVisibility(View.VISIBLE);
                    EEE.setVisibility(View.GONE);
                }else{

                    EEEnoData.setVisibility(View.GONE);
                    EEE.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        EEE_list.add(attributes);

                    }
                    EEE.setHasFixedSize(true);
                    EEE.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(EEE_list, getContext(),"EEE");
                    EEE.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void cseDepartment() {

        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    CSEPageUrl.setText(snapshot.child("CsandEngineering").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "CSE link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("CSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                CSE_list = new ArrayList<>();

                if(!snapshot.exists()){

                    CSEnoData.setVisibility(View.VISIBLE);
                    CSE.setVisibility(View.GONE);
                }else{

                    CSEnoData.setVisibility(View.GONE);
                    CSE.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        CSE_list.add(attributes);

                    }
                    CSE.setHasFixedSize(true);
                    CSE.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(CSE_list, getContext(),"CSE");
                    CSE.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void iseDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    ISEPageUrl.setText(snapshot.child("IsandEngineering").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "ISE link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("ISE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ISE_list = new ArrayList<>();

                if(!snapshot.exists()){

                    ISEnoData.setVisibility(View.VISIBLE);
                    ISE.setVisibility(View.GONE);
                }else{

                    ISEnoData.setVisibility(View.GONE);
                    ISE.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        ISE_list.add(attributes);

                    }
                    ISE.setHasFixedSize(true);
                    ISE.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(ISE_list, getContext(),"ISE");
                    ISE.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void nanoTechDepartment() {


        FirebaseDatabase.getInstance().getReference().child("DepartmentInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    NTechPageUrl.setText(snapshot.child("Nanotechnology").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                Toast.makeText(getContext(), "NTech link unavailable", Toast.LENGTH_SHORT).show();

            }
        });


        dbRef=databaseReference.child("NT");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                NTech_list = new ArrayList<>();

                if(!snapshot.exists()){

                    NTechnoData.setVisibility(View.VISIBLE);
                    NTech.setVisibility(View.GONE);
                }else{

                    NTechnoData.setVisibility(View.GONE);
                    NTech.setVisibility(View.VISIBLE);

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                        FacultyAttributes attributes= dataSnapshot.getValue(FacultyAttributes.class);
                        NTech_list.add(attributes);

                    }
                    NTech.setHasFixedSize(true);
                    NTech.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerAdapter = new FacultyInfoAdapter(NTech_list, getContext(),"NT");
                    NTech.setAdapter(recyclerAdapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}