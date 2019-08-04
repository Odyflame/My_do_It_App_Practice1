package com.myapp.mysamepeldrawer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.myapp.mysampletab.fragment1
import com.myapp.mysampletab.fragment2
import com.myapp.mysampletab.fragment3
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    interface FragmentCallback{
        open fun onFragmentSelected(position: Int, bundle: Bundle)
    }

    lateinit var frag1 : Fragment
    lateinit var frag2 : Fragment
    lateinit var frag3 : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        frag1 = fragment1()
        frag2 = fragment2()
        frag3 = fragment3()

        supportFragmentManager.beginTransaction().add(R.id.container, frag1).commit()
    }
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
           R.id.menu1 -> {
               Toast.makeText(this, "first menu choised", Toast.LENGTH_SHORT).show()
               onFragmentSelected(0, null)
           }
            R.id.menu2 -> {Toast.makeText(this, "second menu choised", Toast.LENGTH_SHORT).show()
             onFragmentSelected(0, null)
            }
            R.id.menu3 -> {
                Toast.makeText(this, "third menu choised", Toast.LENGTH_SHORT).show()
                onFragmentSelected(0, null)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

     fun onFragmentSelected(position : Int, bundle :Bundle?){
        var curFragment : Fragment? = null
        when(position){
            0-> {
                curFragment = frag1; toolbar.title = "첫 번째 화면면"
            }
           1->{
                curFragment = frag2; toolbar.title = "두 번째 화면면"
            }
            2->{
                curFragment = frag3; toolbar.title = "세 번째 화면면"
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, curFragment!!).commit()
    }


}
